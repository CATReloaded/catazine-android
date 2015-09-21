package net.catazine.live.Utility;


import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.catazine.live.models.Article;
import net.catazine.live.models.Author;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

/**
 * Helper Class containing network requests methods.
 *
 * @since 1.0
 */

final public class NetworkHelper implements Callback {
    private final static OkHttpClient client = new OkHttpClient();
    private final static String ARTICLES_HEADER_URL = "http://catazinelive.net/wp-json/wp/v2/posts";
    private final static String PAGE_PARAMETER = "page";
    private final static String AUTHORS_URL = "http://catazinelive.net/wp-json/wp/v2/users";
    private static NetworkResponse responseCallback;

    /**
     * Method that request the main articles info from the API.
     *
     * @param page Used To define the needed page from the server (Might be used in OnScrollListener)
     * @since 1.0
     */
    public static void requestArticlesHeaders(int page, final NetworkResponse responseCallback) {
        NetworkHelper.responseCallback = responseCallback;

        Uri requestArticlesUri = Uri.parse(ARTICLES_HEADER_URL).buildUpon()
                .appendQueryParameter(PAGE_PARAMETER, String.valueOf(page)).build();

        URL requestArticlesUrl = null;

        try {
            requestArticlesUrl = new URL(requestArticlesUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder()
                .url(requestArticlesUrl).build();

        client.newCall(request).enqueue(new Callback() {
            Handler mainHandler = new Handler(Looper.getMainLooper());
            @Override
            public void onFailure(Request request, final IOException e) {
                mainHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        responseCallback.onFailure(e, -1);
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String body = response.body().string();
                try {
                    //use This in Future Adapter
                    final Collection<Article> articlesList = JSONHelper.getArticles(body);
                    mainHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (response.isSuccessful()) {
                                responseCallback.onResponse(articlesList);
                            } else {
                                int code = response.code();
                                responseCallback.onFailure(new ResponseException(code, body), code);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method that request a single article using its id.
     *
     * @param articleId The id of the article to request
     * @since 1.0
     */
    public static void requestArticleById(Long articleId, final NetworkResponse responseCallback) {

        NetworkHelper.responseCallback = responseCallback;

        Uri requestArticlesUri = Uri.parse(ARTICLES_HEADER_URL).buildUpon()
                .appendPath(articleId.toString()).build();

        URL requestArticlesUrl = null;

        try {
            requestArticlesUrl = new URL(requestArticlesUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder()
                .url(requestArticlesUrl).build();

        client.newCall(request).enqueue(new Callback() {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            @Override
            public void onFailure(Request request, final IOException e) {
                mainHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        responseCallback.onFailure(e, -1);
                    }
                });
                e.printStackTrace();
            }


            @Override
            public void onResponse(final Response response) throws IOException {
                final String body = response.body().string();
                try {
                    //use This in Future Adapter
                    final Article article = JSONHelper.getArticle(body);
                    mainHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (response.isSuccessful()) {
                                responseCallback.onResponse(article);
                            } else {
                                int code = response.code();
                                responseCallback.onFailure(new ResponseException(code, body), code);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    /**
     * Method that request a searches for a string in the articles.
     *
     * @param searchWords The string to pass in the search query.
     * @since 1.0
     */
    public static void searchArticlesByText(String searchWords, final NetworkResponse responseCallback) {

        NetworkHelper.responseCallback = responseCallback;

        final String SEARCH_PARAMETER = "filter[s]";

        Uri requestSearchUri = Uri.parse(ARTICLES_HEADER_URL).buildUpon()
                .appendQueryParameter(SEARCH_PARAMETER, searchWords).build();

        URL requestSearchUrl = null;

        try {
            requestSearchUrl = new URL(requestSearchUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder()
                .url(requestSearchUrl).build();
        client.newCall(request).enqueue(new Callback() {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            @Override
            public void onFailure(Request request, final IOException e) {
                mainHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        responseCallback.onFailure(e, -1);
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String body = response.body().string();
                try {
                    final Collection<Article> articlesList = JSONHelper.getArticles(body);
                    mainHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (response.isSuccessful()) {
                                responseCallback.onResponse(articlesList);
                            } else {
                                int code = response.code();
                                responseCallback.onFailure(new ResponseException(code, body), code);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method that request authors from the API.
     *
     * @param page Used To define the needed page from the server (Might be used in OnScrollListener)
     * @since 1.0
     */
    public static void requestAuthors(int page, final NetworkResponse responseCallback) {

        NetworkHelper.responseCallback = responseCallback;

        Uri requestAuthorsUri = Uri.parse(AUTHORS_URL).buildUpon()
                .appendQueryParameter(PAGE_PARAMETER, String.valueOf(page)).build();

        URL requestAuthorsUrl = null;

        try {
            requestAuthorsUrl = new URL(requestAuthorsUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final Request request = new Request.Builder()
                .url(requestAuthorsUrl).build();
        client.newCall(request).enqueue(new Callback() {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            @Override
            public void onFailure(Request request, final IOException e) {
                mainHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        responseCallback.onFailure(e, -1);
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String body = response.body().string();
                try {
                    final Collection<Author> authorsList = JSONHelper.getAuthors(body);
                    mainHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (response.isSuccessful()) {
                                responseCallback.onResponse(authorsList);
                            } else {
                                int code = response.code();
                                responseCallback.onFailure(new ResponseException(code, body), code);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {

    }
}