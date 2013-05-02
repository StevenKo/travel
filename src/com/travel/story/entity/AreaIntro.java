package com.travel.story.entity;

public class AreaIntro {
    int    id;
    String intro;
    String title;

    public AreaIntro() {
        this(1, "", "");
    }

    public AreaIntro(int id, String intro, String title) {
        this.id = id;
        this.intro = intro;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }
}
