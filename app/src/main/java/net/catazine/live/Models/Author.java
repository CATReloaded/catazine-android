package net.catazine.live.Models;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Author implements Parcelable {

    public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    private static final String KEY_AVATARS_OBJ = "avatar_urls";
    private static final String KEY_AVATARS_SMALL_SIZE = "24";
    private static final String KEY_AVATARS_MEDIUM_SIZE = "48";
    private static final String KEY_AVATARS_LARGE_SIZE = "96";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ID = "id";
    private static final String KEY_LINK = "link";
    private static final String KEY_NAME = "name";
    private static final String KEY_POSTS_COUNT = "posts_count";

    private long id;
    private String name;
    private String avatar;
    private String description;
    private String link;
    private int posts_count;

    public Author(long id, String name, String avatar, String description, String link, int posts_count) {
        setId(id);
        setName(name);
        setAvatar(avatar);
        setDescription(description);
        setLink(link);
        setPosts_count(posts_count);
    }

    public Author(long authorId, String name) {
        setId(authorId);
        setName(name);
    }

    protected Author(Parcel in) {
        setId(in.readInt());
        setName(in.readString());
        setAvatar(in.readString());
        setDescription(in.readString());
        setLink(in.readString());
        setPosts_count(in.readInt());
    }

    public static String getKeyPostsCount() {
        return KEY_POSTS_COUNT;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyName() {
        return KEY_NAME;
    }

    public static String getKeyAvatarsObj() {
        return KEY_AVATARS_OBJ;
    }

    public static String getKeyAvatarsSmallSize() {
        return KEY_AVATARS_SMALL_SIZE;
    }

    public static String getKeyAvatarsMediumSize() {
        return KEY_AVATARS_MEDIUM_SIZE;
    }

    public static String getKeyAvatarsLargeSize() {
        return KEY_AVATARS_LARGE_SIZE;
    }

    public static String getKeyDescription() {
        return KEY_DESCRIPTION;
    }

    public static String getKeyLink() {
        return KEY_LINK;
    }

    public static Author getAuthorData(JSONObject authorObj) throws JSONException {
        long id = authorObj.getLong(getKeyId());
        String name = authorObj.getString(getKeyName());
        JSONObject avatarObj = authorObj.getJSONObject(getKeyAvatarsObj());
        String avatar = avatarObj.getString(getKeyAvatarsLargeSize());
        String description = authorObj.getString(getKeyDescription());
        String link = authorObj.getString(getKeyLink());
        int posts = authorObj.getInt(getKeyPostsCount());

        return new Author(id, name, avatar, description, link, posts);
    }

    public int getPosts_count() {
        return posts_count;
    }

    public void setPosts_count(int posts_count) {
        this.posts_count = posts_count;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(getName());
        dest.writeString(getAvatar());
        dest.writeString(getDescription());
        dest.writeString(getLink());
        dest.writeInt(getPosts_count());
    }
}