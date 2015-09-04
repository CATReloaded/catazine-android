package net.catazine.live.models;


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

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AVATARS_OBJ = "avatar_urls";
    private static final String KEY_AVATARS_SMALL_SIZE = "24";
    private static final String KEY_AVATARS_MEDIUM_SIZE = "48";
    private static final String KEY_AVATARS_LARGE_SIZE = "96";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_LINK = "link";

    private long authorId;
    private String name;
    private String avatar;
    private String description;
    private String link;

    public Author(long authorId, String name, String avatar, String description, String link) {
        setAuthorId(authorId);
        setName(name);
        setAvatar(avatar);
        setDescription(description);
        setLink(link);
    }

    protected Author(Parcel in) {
        setAuthorId(in.readInt());
        setName(in.readString());
        setAvatar(in.readString());
        setDescription(in.readString());
        setLink(in.readString());
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

        return new Author(id, name, avatar, description, link);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
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
        dest.writeLong(getAuthorId());
        dest.writeString(getName());
        dest.writeString(getAvatar());
        dest.writeString(getDescription());
        dest.writeString(getLink());
    }
}