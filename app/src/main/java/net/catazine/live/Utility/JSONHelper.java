package net.catazine.live.Utility;

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
            Article article = Article.getMainArticleData(articleObj);
            articles.add(article);
        }
        return articles;
    }

    public Collection<Author> getAuthors(String authorsResponse) throws JSONException {
        Collection<Author> authors = new ArrayList<>();
        JSONArray authorsArray = new JSONArray(authorsResponse);
        for (int i = 0; i < authorsArray.length(); i++) {
            JSONObject authorObj = authorsArray.getJSONObject(i);
            Author author = Author.getAuthorData(authorObj);
            authors.add(author);
        }
        return authors;
    }

}