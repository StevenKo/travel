package com.travel.story.entity;

public class AreaIntro {
    int    id;
    String intro;
    String title;
    int    area_intro_cate_id;

    public AreaIntro() {
        this(1, "", "", 1);
    }

    public AreaIntro(int id, String intro, String title, int area_intro_cate_id) {
        this.id = id;
        this.intro = intro;
        this.title = title;
        this.area_intro_cate_id = area_intro_cate_id;
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

    public int getAreaIntroCateId() {
        return area_intro_cate_id;
    }
}
