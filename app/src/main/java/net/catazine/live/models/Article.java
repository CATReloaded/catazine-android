package net.catazine.live.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
    private int articleId;
    private String title;
    private Author author;
    private String content;
    private String link;
    private String date;
    private String guid;
    private String excerpt;

    public Article() {
    }

    protected Article(Parcel in) {
        this.articleId = in.readInt();
        this.title = in.readString();
        this.author = in.readParcelable(Author.class.getClassLoader());
        this.content = in.readString();
        this.link = in.readString();
        this.date = in.readString();
        this.guid = in.readString();
        this.excerpt = in.readString();
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getGuid() {
        return guid;
    }

    public String getExcerpt() {
        return excerpt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.articleId);
        dest.writeString(this.title);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.content);
        dest.writeString(this.link);
        dest.writeString(this.date);
        dest.writeString(this.guid);
        dest.writeString(this.excerpt);
    }
}
