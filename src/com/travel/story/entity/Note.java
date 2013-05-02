package com.travel.story.entity;

public class Note {
    int    id;
    String title;
    String author;
    String date;
    String pic;
    String content;
    int    read_num;

    public Note() {
        this(1, "", "", "", "", "", 1);
    }

    public Note(int id, String title, String author, String date, String pic, String content, int read_num) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.pic = pic;
        this.content = content;
        this.read_num = read_num;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPic() {
        return pic;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getReadNum() {
        return read_num;
    }
}
