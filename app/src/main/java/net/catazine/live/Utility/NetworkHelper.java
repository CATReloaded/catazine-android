package net.catazine.live.Utility;


import android.net.Uri;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.catazine.live.models.Article;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

/**
 * Created by ahmad on 07/09/15.
 */
public class NetworkHelper {
    private final static OkHttpClient client = new OkHttpClient();
    private final static String ARTICLESHEADERURL = "http://catazinelive.net/wp-json/wp/v2/posts";

    /**
     * @param page Used To define the needed page from the server (Might be used in OnScrollListener)
     */
    public static void requestArticlesHeaders(Integer page) {
        Uri requestArticlesUri = Uri.parse(ARTICLESHEADERURL).buildUpon()
                .appendQueryParameter("page", page.toString()).build();
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

    public static void requestArticleById(Long articleId) {
        Uri requestArticlesUri = Uri.parse(ARTICLESHEADERURL).buildUpon()
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

    // Search
    public static void searchArticlesByText(String searchWords) {
        Uri requestSearchUri = Uri.parse(ARTICLESHEADERURL).buildUpon()
                .appendQueryParameter("filter[s]", searchWords).build();
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


}
