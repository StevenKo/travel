package com.travel.story.entity;

public class Article {
    
	int     id;
	String  title;
	String  author;
	String  date;
    String  content;
    String  picUrl;
    

    public Article() {
        this(1, "", "", "", "", "");
    }

    public Article(int id, String title, String author, String date, String content, String picUrl){
        
    	this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
        this.picUrl = picUrl;
    }

    public void setText(String text) {
        this.content = text;
    }


    public void setTitle(String title) {
        this.title = title;
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
    
    public String getDate() {
        return date;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getPicUrl() {
        return picUrl;
    }
}
