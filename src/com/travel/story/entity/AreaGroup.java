package com.travel.story.entity;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;

public class AreaGroup {

    int                 id;
    String              name;
    int                 group_id;

    static final String message = "[{\"group_id\":1,\"id\":1,\"name\":\"\u4e2d\u570b\u548c\u53f0\u7063\"},{\"group_id\":1,\"id\":2,\"name\":\"\u4e9e\u6d32\"},{\"group_id\":1,\"id\":3,\"name\":\"\u6b50\u6d32\"},{\"group_id\":1,\"id\":4,\"name\":\"\u5176\u5b83\u6d32\"},{\"group_id\":2,\"id\":5,\"name\":\"\u5bb6\u5ead\u6e38\"},{\"group_id\":2,\"id\":6,\"name\":\"\u6c99\u7058\u967d\u5149\"},{\"group_id\":2,\"id\":7,\"name\":\"\u8cfc\u7269\"},{\"group_id\":2,\"id\":8,\"name\":\"\u6d6a\u6f2b\u60c5\u8abf\"},{\"group_id\":2,\"id\":9,\"name\":\"\u6236\u5916\"},{\"group_id\":2,\"id\":10,\"name\":\"\u63a2\u96aa\"},{\"group_id\":2,\"id\":11,\"name\":\"\u6ed1\u96ea\"},{\"group_id\":2,\"id\":12,\"name\":\"\u53e4\u93ae\"},{\"group_id\":2,\"id\":13,\"name\":\"\u6b77\u53f2\"},{\"group_id\":2,\"id\":14,\"name\":\"\u8cde\u82b1\"},{\"group_id\":2,\"id\":15,\"name\":\"\u7f8e\u98df\"},{\"group_id\":2,\"id\":16,\"name\":\"\u6eab\u6cc9Spa\"},{\"group_id\":3,\"id\":17,\"name\":\"\u6700\u4f73\u6d77\u908a\u5ea6\u5047\u5730\"},{\"group_id\":4,\"id\":18,\"name\":\"\u4e2d\u570b\u548c\u53f0\u7063\"},{\"group_id\":4,\"id\":19,\"name\":\"\u570b\u5916\u57ce\u5e02\"}]";

    public AreaGroup() {
        this(-1, "", -1);
    }

    public AreaGroup(int id, String name, int group_id) {
        this.id = id;
        this.name = name;
        this.group_id = group_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGroupId() {
        return group_id;
    }

    public static ArrayList<AreaGroup> getAreaGroups(int group_id) {
        TreeMap map = new TreeMap<Integer, ArrayList<AreaGroup>>();

        try {
            JSONArray jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                int group_id1 = jArray.getJSONObject(i).getInt("group_id");
                AreaGroup ag = new AreaGroup(id, name, group_id1);
                if (map.containsKey(ag.getGroupId())) {
                    ((ArrayList<AreaGroup>) map.get(ag.getGroupId())).add(ag);
                } else {
                    ArrayList<AreaGroup> newlist = new ArrayList<AreaGroup>(10);
                    newlist.add(ag);
                    map.put(ag.getGroupId(), newlist);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (ArrayList<AreaGroup>) map.get(group_id);
    }
}
