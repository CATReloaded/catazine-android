package net.catazine.live.Models;

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

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_GUID = "guid_rendered";
    private static final String KEY_LINK = "link";
    private static final String KEY_TITLE = "post_title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_AUTHOR_ID = "id";
    private static final String KEY_AUTHOR_NAME = "name";
    private static final String KEY_FEATURED_IMAGE = "featured_image";
    private static final String KEY_CONTENT = "post_content";

    private long id;
    private String date;
    private String guid;
    private String link;
    private String title;
    private long author_id;
    private String author_name;
    private String featured_image;
    private String content;

    public Article(long id, String date, String link, String guid, String title,
                   long author_id, String author_name, String featured_image, String content) {
        this.id = id;
        this.date = date;
        this.link = link;
        this.guid = guid;
        this.title = title;
        this.author_id = author_id;
        this.author_name = author_name;
        this.featured_image = featured_image;
        this.content = content;
    }

    public Article(long id, String date, String guid, String link, String title,
                   long author_id, String author_name, String featured_image) {
        this.id = id;
        this.date = date;
        this.guid = guid;
        this.link = link;
        this.title = title;
        this.author_id = author_id;
        this.author_name = author_name;
        this.featured_image = featured_image;
    }


    protected Article(Parcel in) {
        setId(in.readLong());
        setDate(in.readString());
        setLink(in.readString());
        setGuid(in.readString());
        setTitle(in.readString());
        setAuthor_id(in.readLong());
        setAuthor_name(in.readString());
        setFeatured_image(in.readString());
        setContent(in.readString());
    }

    public static String getKeyAuthorId() {
        return KEY_AUTHOR_ID;
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
        long authorID = author.getLong(getKeyAuthorId());
        String authorName = author.getString(getKeyAuthorName());
        String link = articleJsonObject.getString(getKeyLink());
        String date = articleJsonObject.getString(getKeyDate());
        String guid = articleJsonObject.getString(getKeyGuid());
        String featured_image = articleJsonObject.getString(getKeyFeaturedImage());
        String content = articleJsonObject.getString(getKeyContent());

        return new Article(id, date, link, guid, title, authorID, authorName, featured_image, content);
    }

    public static Article getMainArticleData(JSONObject articleJsonObject) throws JSONException {
        Long id = articleJsonObject.getLong(getKeyId());
        String title = articleJsonObject.getString(getKeyTitle());
        JSONObject author = articleJsonObject.getJSONObject(getKeyAuthor());
        long authorID = author.getLong(getKeyAuthorId());
        String authorName = author.getString(getKeyAuthorName());
        String link = articleJsonObject.getString(getKeyLink());
        String date = articleJsonObject.getString(getKeyDate());
        String guid = articleJsonObject.getString(getKeyGuid());
        String featured_image = articleJsonObject.getString(getKeyFeaturedImage());

        return new Article(id, date, link, guid, title, authorID, authorName, featured_image);
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
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
        dest.writeString(getDate());
        dest.writeString(getLink());
        dest.writeString(getGuid());
        dest.writeString(getTitle());
        dest.writeLong(getAuthor_id());
        dest.writeString(getAuthor_name());
        dest.writeString(getFeatured_image());
        dest.writeString(getContent());
    }
}