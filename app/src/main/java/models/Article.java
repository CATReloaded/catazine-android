package models;


public class Article {
    private int articleId;
    private String title;
    private Author author;
    private String content;
    private String link;
    private String date;
    private String guid;
    private String excerpt;

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
}
