package net.catazine.live.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable {
    private int authorId;
    private String userName;
    private String name;
    private String avatar;
    private String description;

    public int getAuthorId() {
        return authorId;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.authorId);
        dest.writeString(this.userName);
        dest.writeString(this.name);
        dest.writeString(this.avatar);
        dest.writeString(this.description);
    }

    public Author() {
    }

    protected Author(Parcel in) {
        this.authorId = in.readInt();
        this.userName = in.readString();
        this.name = in.readString();
        this.avatar = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
