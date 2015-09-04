package net.catazine.live.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Article implements Parcelable {
    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
    private static final String KEY_ID = "ID";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_AUTHOR_NAME = "name";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_LINK = "link";
    private static final String KEY_DATE = "date";
    private static final String KEY_GUID = "guid";
    private static final String KEY_FEATURED_IMAGE = "featured_image";
    private long id;
    private String title;
    private String author;
    private String content;
    private String link;
    private String date;
    private String guid;
    private String featured_image;

    public Article(long articleId, String title, String author,
                   String link, String date, String guid, String featured_image) {
        this.id = articleId;
        this.title = title;
        this.author = author;
        this.link = link;
        this.date = date;
        this.guid = guid;
        this.featured_image = featured_image;
    }

    public Article(long articleId, String title, String author, String content,
                   String link, String date, String guid, String featured_image) {
        this.id = articleId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.link = link;
        this.date = date;
        this.guid = guid;
        this.featured_image = featured_image;
    }

    protected Article(Parcel in) {
        setId(in.readInt());
        setTitle(in.readString());
        setAuthor(in.readString());
        setLink(in.readString());
        setDate(in.readString());
        setGuid(in.readString());
        setFeatured_image(in.readString());
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyTitle() {
        return KEY_TITLE;
    }

    public static String getKeyAuthor() {
        return KEY_AUTHOR;
    }

    public static String getKeyAuthorName() {
        return KEY_AUTHOR_NAME;
    }

    public static String getKeyContent() {
        return KEY_CONTENT;
    }

    public static String getKeyDate() {
        return KEY_DATE;
    }

    public static String getKeyLink() {
        return KEY_LINK;
    }

    public static String getKeyGuid() {
        return KEY_GUID;
    }

    public static String getKeyFeaturedImage() {
        return KEY_FEATURED_IMAGE;
    }

    public static Article getAllArticleData(JSONObject articleJsonObject) throws JSONException {
        Long id = articleJsonObject.getLong(getKeyId());
        String title = articleJsonObject.getString(getKeyTitle());
        JSONObject author = articleJsonObject.getJSONObject(getKeyAuthor());
        String authorName = author.getString(getKeyAuthorName());
        String link = articleJsonObject.getString(getKeyLink());
        String date = articleJsonObject.getString(getKeyDate());
        String guid = articleJsonObject.getString(getKeyGuid());
        JSONObject featuredImageObject = articleJsonObject.getJSONObject(getKeyFeaturedImage());
        String featured_image = featuredImageObject.getString(getKeyGuid());

        return new Article(id, title, authorName, link, date, guid, featured_image);
    }

    public static Article getMainArticleData(JSONObject articleJsonObject) throws JSONException {
        Long id = articleJsonObject.getLong(getKeyId());
        String title = articleJsonObject.getString(getKeyTitle());
        JSONObject author = articleJsonObject.getJSONObject(getKeyAuthor());
        String authorName = author.getString(getKeyAuthorName());
        String content = articleJsonObject.getString(getKeyContent());
        String link = articleJsonObject.getString(getKeyLink());
        String date = articleJsonObject.getString(getKeyDate());
        String guid = articleJsonObject.getString(getKeyGuid());
        JSONObject featuredImageObject = articleJsonObject.getJSONObject(getKeyFeaturedImage());
        String featured_image = featuredImageObject.getString(getKeyGuid());

        return new Article(id, title, authorName, content, link, date, guid, featured_image);
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(getTitle());
        dest.writeString(getAuthor());
        dest.writeString(getLink());
        dest.writeString(getDate());
        dest.writeString(getGuid());
        dest.writeString(getFeatured_image());
    }
}