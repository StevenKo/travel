package com.travel.story.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class State {
    int                 id;
    String              name;
    String              name_cn;

    static final String message = "[{\"id\":1,\"name\":\"\u4e2d\u570b\u548c\u53f0\u7063\",\"name_cn\":\"\u4e2d\u56fd\"},{\"id\":2,\"name\":\"\u4e9e\u6d32\",\"name_cn\":\"\u4e9a\u6d32\"},{\"id\":3,\"name\":\"\u6b50\u6d32\",\"name_cn\":\"\u6b27\u6d32\"},{\"id\":4,\"name\":\"\u5927\u6d0b\u5dde\",\"name_cn\":\"\u5927\u6d0b\u5dde\"},{\"id\":5,\"name\":\"\u975e\u6d32\",\"name_cn\":\"\u975e\u6d32\"},{\"id\":6,\"name\":\"\u5317\u7f8e\u6d32\",\"name_cn\":\"\u5317\u7f8e\u6d32\"},{\"id\":7,\"name\":\"\u5357\u7f8e\u6d32\",\"name_cn\":\"\u5357\u7f8e\u6d32\"},{\"id\":8,\"name\":\"\u5357\u6975\",\"name_cn\":\"\u5357\u6781\"}]";

    public State() {
        this(-1, "", "");
    }

    public State(int id, String name, String name_cn) {
        this.id = id;
        this.name = name;
        this.name_cn = name_cn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<State> getStates() {
        ArrayList<State> newlist = new ArrayList<State>(10);

        JSONArray jArray;
        try {
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                String name_cn = jArray.getJSONObject(i).getString("name_cn");
                State state = new State(id, name, name_cn);
                newlist.add(state);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newlist;
    }

}
