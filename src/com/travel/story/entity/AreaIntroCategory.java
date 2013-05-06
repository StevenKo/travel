package com.travel.story.entity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

public class AreaIntroCategory {
    int                 id;
    String              name;

    static final String message = "[{\"id\":1,\"name\":\"\u80cc\u666f\"},{\"id\":2,\"name\":\"\u80cc\u666f\"},{\"id\":3,\"name\":\"\u80cc\u666f\"},{\"id\":4,\"name\":\"\u80cc\u666f\"},{\"id\":5,\"name\":\"\u80cc\u666f\"},{\"id\":6,\"name\":\"\u80cc\u666f\"},{\"id\":7,\"name\":\"\u80cc\u666f\"},{\"id\":8,\"name\":\"\u80cc\u666f\"},{\"id\":9,\"name\":\"\u80cc\u666f\"},{\"id\":10,\"name\":\"\u80cc\u666f\"},{\"id\":11,\"name\":\"\u80cc\u666f\"},{\"id\":12,\"name\":\"\u80cc\u666f\"},{\"id\":13,\"name\":\"\u80cc\u666f\"},{\"id\":14,\"name\":\"\u80cc\u666f\"},{\"id\":15,\"name\":\"\u80cc\u666f\"},{\"id\":16,\"name\":\"\u80cc\u666f\"},{\"id\":17,\"name\":\"\u80cc\u666f\"},{\"id\":18,\"name\":\"\u80cc\u666f\"},{\"id\":19,\"name\":\"\u4ea4\u901a\"},{\"id\":20,\"name\":\"\u4ea4\u901a\"},{\"id\":21,\"name\":\"\u4ea4\u901a\"},{\"id\":22,\"name\":\"\u4ea4\u901a\"},{\"id\":23,\"name\":\"\u4ea4\u901a\"},{\"id\":24,\"name\":\"\u4ea4\u901a\"},{\"id\":25,\"name\":\"\u4ea4\u901a\"},{\"id\":26,\"name\":\"\u4ea4\u901a\"},{\"id\":27,\"name\":\"\u4ea4\u901a\"},{\"id\":28,\"name\":\"\u73a9\"},{\"id\":29,\"name\":\"\u73a9\"},{\"id\":30,\"name\":\"\u73a9\"},{\"id\":31,\"name\":\"\u73a9\"},{\"id\":32,\"name\":\"\u73a9\"},{\"id\":33,\"name\":\"\u73a9\"},{\"id\":34,\"name\":\"\u73a9\"},{\"id\":35,\"name\":\"\u73a9\"},{\"id\":36,\"name\":\"\u73a9\"},{\"id\":37,\"name\":\"\u5403\"},{\"id\":38,\"name\":\"\u5403\"},{\"id\":39,\"name\":\"\u5403\"},{\"id\":40,\"name\":\"\u5403\"},{\"id\":41,\"name\":\"\u5403\"},{\"id\":42,\"name\":\"\u5403\"},{\"id\":43,\"name\":\"\u5403\"},{\"id\":44,\"name\":\"\u5403\"},{\"id\":45,\"name\":\"\u4f4f\"},{\"id\":46,\"name\":\"\u4f4f\"}]";

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
