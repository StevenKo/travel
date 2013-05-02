package com.travel.story.entity;

public class Area {
    int    id;
    String name;
    String name_cn;
    String pic;

    public Area() {
        this(1, "", "", "");
    }

    public Area(int id, String name, String name_cn, String pic) {
        this.id = id;
        this.name = name;
        this.name_cn = name_cn;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameCn() {
        return name_cn;
    }

    public String getPic() {
        return pic;
    }
}
