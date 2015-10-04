package net.catazine.live.Utility;

import net.catazine.live.Models.Article;
import net.catazine.live.Models.Author;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Helper Class containing JSON handling methods.
 *
 * @since 1.0
 */
public class JSONHelper {

    /**
     * Method that gets all the articles.
     *
     * @param postsResponse a string that returns from `requestArticlesHeaders` method in `NetworkHelper` class.
     * @return a collection of articles containing all fields except the `post_content` of every article.
     * @throws JSONException
     * @since 1.0
     */
    public static Collection<Article> getArticles(String postsResponse) throws JSONException {
        Collection<Article> articles = new ArrayList<>();
        JSONArray articlesArray = new JSONArray(postsResponse);
        for (int i = 0; i < articlesArray.length(); i++) {
            JSONObject articleObj = articlesArray.getJSONObject(i);
            Article article = Article.getMainArticleData(articleObj);
            articles.add(article);
        }
        return articles;
    }


    /**
     * Method that gets a single article.
     *
     * @param postsResponse a string that returns from `requestArticleById` method in `NetworkHelper` class.
     * @return an article object congaing all article fields including `post_content`.
     * @throws JSONException
     * @since 1.0
     */
    public static Article getArticle(String postsResponse) throws JSONException {
        JSONObject articleObj = new JSONObject(postsResponse);
        Article article = Article.getAllArticleData(articleObj);
        return article;
    }

    /**
     * Method that gets all the authors of CATaZie.
     *
     * @param authorsResponse a string that returns from `requestAuthors` method in `NetworkHelper` class.
     * @return a collection of authors containing.
     * @throws JSONException
     * @since 1.0
     */
    public static Collection<Author> getAuthors(String authorsResponse) throws JSONException {
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