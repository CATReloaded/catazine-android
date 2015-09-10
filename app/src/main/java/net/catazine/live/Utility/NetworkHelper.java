package net.catazine.live.Utility;


import android.net.Uri;

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

public class NetworkHelper {
    private final static OkHttpClient client = new OkHttpClient();
    private final static String ARTICLES_HEADER_URL = "http://catazinelive.net/wp-json/wp/v2/posts";
    private final static String PAGE_PARAMETER = "page";
    private final static String AUTHORS_URL = "http://catazinelive.net/wp-json/wp/v2/users";


    /**
     * Method that request the main articles info from the API.
     *
     * @param page Used To define the needed page from the server (Might be used in OnScrollListener)
     * @since 1.0
     */
    public static void requestArticlesHeaders(int page) {

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
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    //use This in Future Adapter
                    Collection<Article> articlesList = JSONHelper.getArticles(response.body().string());
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
    public static void requestArticleById(Long articleId) {
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
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    //use This in Future Adapter
                    Article article = JSONHelper.getArticle(response.body().string());

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
    public static void searchArticlesByText(String searchWords) {
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
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {

                    Collection<Article> articlesList = JSONHelper.getArticles(response.body().string());

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
    public static void requestAuthors(int page) {

        Uri requestAuthorsUri = Uri.parse(AUTHORS_URL).buildUpon()
                .appendQueryParameter(PAGE_PARAMETER, String.valueOf(page)).build();
        URL requestAuthorsUrl = null;
        try {
            requestAuthorsUrl = new URL(requestAuthorsUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder()
                .url(requestAuthorsUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    //use This in Future Adapter
                    Collection<Author> authorsList = JSONHelper.getAuthors(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}