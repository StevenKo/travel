package com.travel.story.entity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

public class AreaIntroCategory {
    int                 id;
    String              name;

    static final String message = "[{\"id\":1,\"name\":\"\u80cc\u666f\"},{\"id\":2,\"name\":\"\u80cc\u666f\"},{\"id\":3,\"name\":\"\u4ea4\u901a\"},{\"id\":4,\"name\":\"\u73a9\"},{\"id\":5,\"name\":\"\u5403\"},{\"id\":6,\"name\":\"\u4f4f\"},{\"id\":7,\"name\":\"\u4f4f\"}]";

    public AreaIntroCategory() {
        this(-1, "");
    }

    public AreaIntroCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getCategoryName(int id) {
        HashMap hash = new HashMap();
        JSONArray categoryArray;
        try {
            categoryArray = new JSONArray(message.toString());
            for (int i = 0; i < categoryArray.length(); i++) {
                int category_id = categoryArray.getJSONObject(i).getInt("id");
                String name = categoryArray.getJSONObject(i).getString("name");
                hash.put(category_id, name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (String) hash.get(id);
    }

    public static ArrayList<AreaIntroCategory> getAreaIntroCategories() {
        ArrayList<AreaIntroCategory> cateogries = new ArrayList<AreaIntroCategory>();
        JSONArray categoryArray;
        try {
            categoryArray = new JSONArray(message.toString());
            for (int i = 0; i < categoryArray.length(); i++) {
                int category_id = categoryArray.getJSONObject(i).getInt("id");
                String name = categoryArray.getJSONObject(i).getString("name");
                AreaIntroCategory cat = new AreaIntroCategory(category_id, name);
                cateogries.add(cat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cateogries;
    }

}
