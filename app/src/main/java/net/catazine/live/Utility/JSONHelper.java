package net.catazine.live.Utility;

import android.util.Log;

import net.catazine.live.models.Article;
import net.catazine.live.models.Author;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class JSONHelper {

    public Collection<Article> getArticles(String postsResponse) throws JSONException {
        Collection<Article> articles = new ArrayList<>();
        JSONArray articlesArray = new JSONArray(postsResponse);
        for (int i = 0; i < articlesArray.length(); i++) {
            JSONObject articleObj = articlesArray.getJSONObject(i);
            Article article = Article.getAllArticleData(articleObj);
            articles.add(article);
            Log.i("title", article.getTitle());
            Log.i("link", article.getLink());
            Log.i("id", article.getId()+"");
            Log.i("img", article.getFeatured_image());
            Log.i("guid", article.getGuid());
        }
        return articles;
    }


    public Article getArticle(String postsResponse) throws JSONException {
        JSONObject articleObj = new JSONObject(postsResponse);
        Article article = Article.getAllArticleData(articleObj);
        Log.i("title", article.getTitle());
        Log.i("link", article.getLink());
        Log.i("id", article.getId()+"");
        Log.i("img", article.getFeatured_image());
        Log.i("guid", article.getGuid());
        Log.i("content", article.getContent());
        return article;
    }

    public Collection<Author> getAuthors(String authorsResponse) throws JSONException {
        Collection<Author> authors = new ArrayList<>();
        JSONArray authorsArray = new JSONArray(authorsResponse);
        for (int i = 0; i < authorsArray.length(); i++) {
            JSONObject authorObj = authorsArray.getJSONObject(i);
            Author author = Author.getAuthorData(authorObj);
            authors.add(author);
            Log.i("NAME", author.getName());
            Log.i("ID", author.getId()+"");
            Log.i("DESC", author.getDescription());
            Log.i("AVATAR", author.getAvatar());
            Log.i("LINK", author.getLink());
            Log.i("COUNT", author.getPosts_count()+"");
        }
        return authors;
    }

}