package com.travel.story.entity;

public class Site {

    int             id;
    int             starInt;  // from 0 to 5
    String          name;
    String          info;
    String          pic;
    String[]        pics;
    String          introduce;

    static String[] k = {};

    public Site(int id2, int rank, String name2, String pic, String info, String intro, String[] pics2) {
        this.id = id2;
        this.starInt = rank;
        this.pic = pic;
        this.pics = pics2;
        this.info = info;
        this.name = name2;
        this.introduce = intro;
    }

    public int getId() {
        return id;
    }

    public int getStarInt() {
        return starInt;
    }

    public String getName() {
        return name;
    }

    public String[] getPics() {
        return pics;
    }

    public String getPic() {
        return pic;
    }

    public String getInfo() {
        return info;
    }

    public String getIntroduction() {
        return introduce;
    }
}
