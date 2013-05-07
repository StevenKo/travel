package com.travel.story.entity;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;

public class NationGroup {

    int                 id;
    String              name;
    int                 state_id;

    static final String message = "[{\"id\":1,\"name\":\"\u5357\u6975\",\"state_id\":8},{\"id\":2,\"name\":\"\u96f2\u5357\u3001\u56db\u5ddd\u3001\u897f\u85cf\u3001\u8cb4\u5dde\",\"state_id\":1},{\"id\":3,\"name\":\"\u6e56\u5357\u3001\u6e56\u5317\",\"state_id\":1},{\"id\":4,\"name\":\"\u65b0\u7586\u3001\u9752\u6d77\u3001\u7518\u8085\u3001\u5be7\u590f\u3001\u965d\u897f\",\"state_id\":1},{\"id\":5,\"name\":\"\u5be7\u590f\u3001\u5167\u8499\u53e4\",\"state_id\":1},{\"id\":6,\"name\":\"\u9999\u6e2f\u3001\u6fb3\u9580\u3001\u53f0\u7063\",\"state_id\":1},{\"id\":7,\"name\":\"\u6d77\u5357\u4e09\u4e9e\",\"state_id\":1},{\"id\":8,\"name\":\"\u4e0a\u6d77\u53ca\u5468\u908a\",\"state_id\":1},{\"id\":9,\"name\":\"\u798f\u5efa\u5ec8\u9580\",\"state_id\":1},{\"id\":10,\"name\":\"\u5ee3\u5dde\u53ca\u5468\u908a\",\"state_id\":1},{\"id\":11,\"name\":\"\u5ee3\u897f\u6842\u6797\u3001\u967d\u6714\",\"state_id\":1},{\"id\":12,\"name\":\"\u5317\u4eac\u53ca\u5468\u908a\",\"state_id\":1},{\"id\":13,\"name\":\"\u5c71\u897f\u3001\u6cb3\u5357\u3001\u6cb3\u5317\",\"state_id\":1},{\"id\":14,\"name\":\"\u9ed1\u9f8d\u6c5f\u3001\u5409\u6797\u3001\u907c\u5be7\",\"state_id\":1},{\"id\":15,\"name\":\"\u65e5\u672c\u3001\u97d3\u570b\u3001\u671d\u9bae\",\"state_id\":2},{\"id\":16,\"name\":\"\u65b0\u52a0\u5761\u3001\u99ac\u4f86\u897f\u4e9e\u3001\u6587\u840a\",\"state_id\":2},{\"id\":17,\"name\":\"\u6cf0\u570b\",\"state_id\":2},{\"id\":19,\"name\":\"\u5370\u5ea6\u5c3c\u897f\u4e9e\",\"state_id\":2},{\"id\":20,\"name\":\"\u83f2\u5f8b\u8cd3\u3001\u95dc\u5cf6\u3001\u585e\u73ed\u3001\u5929\u5be7\",\"state_id\":2},{\"id\":21,\"name\":\"\u5370\u5ea6\u3001\u5df4\u57fa\u65af\u5766\",\"state_id\":2},{\"id\":22,\"name\":\"\u5c3c\u6cca\u723e\u3001\u4e0d\u4e39\",\"state_id\":2},{\"id\":23,\"name\":\"\u99ac\u723e\u4ee3\u592b\u3001\u65af\u91cc\u862d\u5361\",\"state_id\":2},{\"id\":24,\"name\":\"\u897f\u4e9e\u5404\u570b\",\"state_id\":2},{\"id\":25,\"name\":\"\u6cd5\u570b\u3001\u6469\u7d0d\u54e5\u3001\u745e\u58eb\",\"state_id\":3},{\"id\":26,\"name\":\"\u5fb7\u570b\u3001\u5967\u5730\u5229\u3001\u5308\u7259\u5229\u3001\u6377\u514b\",\"state_id\":3},{\"id\":27,\"name\":\"\u610f\u5927\u5229\u3001\u8056\u99ac\u529b\u8afe\u3001\u68b5\u8482\u5ca1\",\"state_id\":3},{\"id\":28,\"name\":\"\u82f1\u570b\u3001\u611b\u723e\u862d\",\"state_id\":3},{\"id\":29,\"name\":\"\u8377\u862d\u3001\u6bd4\u5229\u6642\u3001\u76e7\u68ee\u5821\",\"state_id\":3},{\"id\":30,\"name\":\"\u632a\u5a01\u3001\u745e\u5178\u3001\u82ac\u862d\u3001\u4e39\u9ea5\u3001\u51b0\u5cf6\",\"state_id\":3},{\"id\":31,\"name\":\"\u897f\u73ed\u7259\u3001\u8461\u8404\u7259\",\"state_id\":3},{\"id\":32,\"name\":\"\u5e0c\u81d8\u3001\u571f\u8033\u5176\u3001\u8cfd\u666e\u52d2\u65af\",\"state_id\":3},{\"id\":33,\"name\":\"\u6fb3\u5927\u5229\u4e9e\",\"state_id\":4},{\"id\":34,\"name\":\"\u65b0\u897f\u862d\u53ca\u5357\u592a\u5e73\u6d0b\u5cf6\u570b\",\"state_id\":4},{\"id\":35,\"name\":\"\u7f8e\u570b\u3001\u52a0\u62ff\u5927\",\"state_id\":6},{\"id\":36,\"name\":\"\u58a8\u897f\u54e5\u3001\u53e4\u5df4\",\"state_id\":6},{\"id\":37,\"name\":\"\u5df4\u897f\u3001\u963f\u6839\u5ef7\u3001\u79d8\u9b6f\u3001\u667a\u5229\",\"state_id\":7},{\"id\":38,\"name\":\"\u5317\u975e\",\"state_id\":5},{\"id\":39,\"name\":\"\u5357\u975e\",\"state_id\":5},{\"id\":40,\"name\":\"\u6771\u975e\",\"state_id\":5},{\"id\":41,\"name\":\"\u5176\u5b83\",\"state_id\":1},{\"id\":42,\"name\":\"\u5176\u5b83\",\"state_id\":2},{\"id\":43,\"name\":\"\u5176\u5b83\",\"state_id\":3},{\"id\":44,\"name\":\"\u5176\u5b83\",\"state_id\":4},{\"id\":45,\"name\":\"\u5176\u5b83\",\"state_id\":5},{\"id\":46,\"name\":\"\u5176\u5b83\",\"state_id\":6},{\"id\":47,\"name\":\"\u5176\u5b83\",\"state_id\":7},{\"id\":49,\"name\":\"\u5176\u5b83\",\"state_id\":9}]";

    public NationGroup() {
        this(-1, "", -1);
    }

    public NationGroup(int id, String name, int state_id) {
        this.id = id;
        this.name = name;
        this.state_id = state_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSteteId() {
        return state_id;
    }

    public static ArrayList<NationGroup> getSteteNationGroups(int state_id) {
        TreeMap map = new TreeMap<Integer, ArrayList<NationGroup>>();

        try {
            JSONArray jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                int state_id1 = jArray.getJSONObject(i).getInt("state_id");
                NationGroup ag = new NationGroup(id, name, state_id1);
                if (map.containsKey(ag.getSteteId())) {
                    ((ArrayList<NationGroup>) map.get(ag.getSteteId())).add(ag);
                } else {
                    ArrayList<NationGroup> newlist = new ArrayList<NationGroup>(10);
                    newlist.add(ag);
                    map.put(ag.getSteteId(), newlist);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (ArrayList<NationGroup>) map.get(state_id);
    }
}
