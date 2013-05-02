package com.travel.story.entity;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;

public class Nation {
    int                 id;
    String              name;
    String              name_cn;
    int                 state_id;
    int                 nation_group_id;

    static final String message = "[{\"id\":1,\"name\":\"\u5357\u6781\",\"name_cn\":\"\u5357\u6781\",\"nation_group_id\":1,\"state_id\":8},{\"id\":2,\"name\":\"\u9ed1\u9f8d\u6c5f\",\"name_cn\":\"\u9ed1\u9f99\u6c5f\",\"nation_group_id\":14,\"state_id\":1},{\"id\":3,\"name\":\"\u5409\u6797\",\"name_cn\":\"\u5409\u6797\",\"nation_group_id\":14,\"state_id\":1},{\"id\":4,\"name\":\"\u907c\u5be7\",\"name_cn\":\"\u8fbd\u5b81\",\"nation_group_id\":14,\"state_id\":1},{\"id\":5,\"name\":\"\u5167\u8499\u53e4\",\"name_cn\":\"\u5185\u8499\u53e4\",\"nation_group_id\":5,\"state_id\":1},{\"id\":6,\"name\":\"\u65b0\u7586\",\"name_cn\":\"\u65b0\u7586\",\"nation_group_id\":4,\"state_id\":1},{\"id\":7,\"name\":\"\u6566\u714c\",\"name_cn\":\"\u6566\u714c\",\"nation_group_id\":null,\"state_id\":1},{\"id\":8,\"name\":\"\u5317\u4eac\",\"name_cn\":\"\u5317\u4eac\",\"nation_group_id\":null,\"state_id\":1},{\"id\":9,\"name\":\"\u5929\u6d25\",\"name_cn\":\"\u5929\u6d25\",\"nation_group_id\":null,\"state_id\":1},{\"id\":10,\"name\":\"\u6cb3\u5317\",\"name_cn\":\"\u6cb3\u5317\",\"nation_group_id\":13,\"state_id\":1},{\"id\":11,\"name\":\"\u5be7\u590f\",\"name_cn\":\"\u5b81\u590f\",\"nation_group_id\":5,\"state_id\":1},{\"id\":12,\"name\":\"\u9752\u6d77\",\"name_cn\":\"\u9752\u6d77\",\"nation_group_id\":4,\"state_id\":1},{\"id\":13,\"name\":\"\u897f\u85cf\",\"name_cn\":\"\u897f\u85cf\",\"nation_group_id\":2,\"state_id\":1},{\"id\":14,\"name\":\"\u965d\u897f\",\"name_cn\":\"\u9655\u897f\",\"nation_group_id\":4,\"state_id\":1},{\"id\":15,\"name\":\"\u6cb3\u5357\",\"name_cn\":\"\u6cb3\u5357\",\"nation_group_id\":13,\"state_id\":1},{\"id\":16,\"name\":\"\u5c71\u6771\",\"name_cn\":\"\u5c71\u4e1c\",\"nation_group_id\":null,\"state_id\":1},{\"id\":17,\"name\":\"\u5c71\u897f\",\"name_cn\":\"\u5c71\u897f\",\"nation_group_id\":13,\"state_id\":1},{\"id\":18,\"name\":\"\u7518\u8085\",\"name_cn\":\"\u7518\u8083\",\"nation_group_id\":4,\"state_id\":1},{\"id\":19,\"name\":\"\u6c5f\u8607\",\"name_cn\":\"\u6c5f\u82cf\",\"nation_group_id\":null,\"state_id\":1},{\"id\":20,\"name\":\"\u62c9\u85a9\",\"name_cn\":\"\u62c9\u8428\",\"nation_group_id\":null,\"state_id\":1},{\"id\":21,\"name\":\"\u6210\u90fd\",\"name_cn\":\"\u6210\u90fd\",\"nation_group_id\":null,\"state_id\":1},{\"id\":22,\"name\":\"\u6e56\u5317\",\"name_cn\":\"\u6e56\u5317\",\"nation_group_id\":3,\"state_id\":1},{\"id\":23,\"name\":\"\u5b89\u5fbd\",\"name_cn\":\"\u5b89\u5fbd\",\"nation_group_id\":null,\"state_id\":1},{\"id\":24,\"name\":\"\u4e0a\u6d77\",\"name_cn\":\"\u4e0a\u6d77\",\"nation_group_id\":null,\"state_id\":1},{\"id\":25,\"name\":\"\u56db\u5ddd\",\"name_cn\":\"\u56db\u5ddd\",\"nation_group_id\":2,\"state_id\":1},{\"id\":26,\"name\":\"\u91cd\u6176\",\"name_cn\":\"\u91cd\u5e86\",\"nation_group_id\":null,\"state_id\":1},{\"id\":27,\"name\":\"\u8cb4\u5dde\",\"name_cn\":\"\u8d35\u5dde\",\"nation_group_id\":2,\"state_id\":1},{\"id\":28,\"name\":\"\u6e56\u5357\",\"name_cn\":\"\u6e56\u5357\",\"nation_group_id\":3,\"state_id\":1},{\"id\":29,\"name\":\"\u6d59\u6c5f\",\"name_cn\":\"\u6d59\u6c5f\",\"nation_group_id\":null,\"state_id\":1},{\"id\":30,\"name\":\"\u6c5f\u897f\",\"name_cn\":\"\u6c5f\u897f\",\"nation_group_id\":null,\"state_id\":1},{\"id\":31,\"name\":\"\u798f\u5efa\",\"name_cn\":\"\u798f\u5efa\",\"nation_group_id\":null,\"state_id\":1},{\"id\":32,\"name\":\"\u9e97\u6c5f\",\"name_cn\":\"\u4e3d\u6c5f\",\"nation_group_id\":null,\"state_id\":1},{\"id\":33,\"name\":\"\u6842\u6797\",\"name_cn\":\"\u6842\u6797\",\"nation_group_id\":null,\"state_id\":1},{\"id\":34,\"name\":\"\u5ee3\u6771\",\"name_cn\":\"\u5e7f\u4e1c\",\"nation_group_id\":null,\"state_id\":1},{\"id\":35,\"name\":\"\u53f0\u7063\",\"name_cn\":\"\u53f0\u6e7e\",\"nation_group_id\":6,\"state_id\":1},{\"id\":36,\"name\":\"\u96f2\u5357\",\"name_cn\":\"\u4e91\u5357\",\"nation_group_id\":2,\"state_id\":1},{\"id\":37,\"name\":\"\u5ee3\u897f\",\"name_cn\":\"\u5e7f\u897f\",\"nation_group_id\":null,\"state_id\":1},{\"id\":38,\"name\":\"\u9999\u6e2f\",\"name_cn\":\"\u9999\u6e2f\",\"nation_group_id\":6,\"state_id\":1},{\"id\":39,\"name\":\"\u6fb3\u9580\",\"name_cn\":\"\u6fb3\u95e8\",\"nation_group_id\":6,\"state_id\":1},{\"id\":40,\"name\":\"\u6d77\u5357\",\"name_cn\":\"\u6d77\u5357\",\"nation_group_id\":null,\"state_id\":1},{\"id\":41,\"name\":\"\u65e5\u672c\",\"name_cn\":\"\u65e5\u672c\",\"nation_group_id\":15,\"state_id\":2},{\"id\":42,\"name\":\"\u671d\u9bae\",\"name_cn\":\"\u671d\u9c9c\",\"nation_group_id\":15,\"state_id\":2},{\"id\":43,\"name\":\"\u97d3\u570b\",\"name_cn\":\"\u97e9\u56fd\",\"nation_group_id\":15,\"state_id\":2},{\"id\":44,\"name\":\"\u7d04\u65e6\",\"name_cn\":\"\u7ea6\u65e6\",\"nation_group_id\":null,\"state_id\":2},{\"id\":45,\"name\":\"\u5370\u5ea6\",\"name_cn\":\"\u5370\u5ea6\",\"nation_group_id\":21,\"state_id\":2},{\"id\":46,\"name\":\"\u7dec\u7538\",\"name_cn\":\"\u7f05\u7538\",\"nation_group_id\":null,\"state_id\":2},{\"id\":47,\"name\":\"\u8001\u64be\",\"name_cn\":\"\u8001\u631d\",\"nation_group_id\":null,\"state_id\":2},{\"id\":48,\"name\":\"\u6cf0\u570b\",\"name_cn\":\"\u6cf0\u56fd\",\"nation_group_id\":17,\"state_id\":2},{\"id\":49,\"name\":\"\u8d8a\u5357\",\"name_cn\":\"\u8d8a\u5357\",\"nation_group_id\":null,\"state_id\":2},{\"id\":50,\"name\":\"\u4fc4\u7f85\u65af\",\"name_cn\":\"\u4fc4\u7f57\u65af\",\"nation_group_id\":null,\"state_id\":2},{\"id\":51,\"name\":\"\u571f\u8033\u5176\",\"name_cn\":\"\u571f\u8033\u5176\",\"nation_group_id\":32,\"state_id\":2},{\"id\":52,\"name\":\"\u4ee5\u8272\u5217\",\"name_cn\":\"\u4ee5\u8272\u5217\",\"nation_group_id\":null,\"state_id\":2},{\"id\":53,\"name\":\"\u5c3c\u6cca\u723e\",\"name_cn\":\"\u5c3c\u6cca\u5c14\",\"nation_group_id\":22,\"state_id\":2},{\"id\":54,\"name\":\"\u963f\u806f\",\"name_cn\":\"\u963f\u8054\u914b\",\"nation_group_id\":null,\"state_id\":2},{\"id\":55,\"name\":\"\u67ec\u57d4\u5be8\",\"name_cn\":\"\u67ec\u57d4\u5be8\",\"nation_group_id\":null,\"state_id\":2},{\"id\":56,\"name\":\"\u83f2\u5f8b\u8cd3\",\"name_cn\":\"\u83f2\u5f8b\u5bbe\",\"nation_group_id\":20,\"state_id\":2},{\"id\":57,\"name\":\"\u65b0\u52a0\u5761\",\"name_cn\":\"\u65b0\u52a0\u5761\",\"nation_group_id\":16,\"state_id\":2},{\"id\":58,\"name\":\"\u99ac\u723e\u4ee3\u592b\",\"name_cn\":\"\u9a6c\u5c14\u4ee3\u592b\",\"nation_group_id\":23,\"state_id\":2},{\"id\":59,\"name\":\"\u65af\u91cc\u862d\u5361\",\"name_cn\":\"\u65af\u91cc\u5170\u5361\",\"nation_group_id\":23,\"state_id\":2},{\"id\":60,\"name\":\"\u99ac\u4f86\u897f\u4e9e\",\"name_cn\":\"\u9a6c\u6765\u897f\u4e9a\",\"nation_group_id\":16,\"state_id\":2},{\"id\":61,\"name\":\"\u5370\u5ea6\u5c3c\u897f\u4e9e\",\"name_cn\":\"\u5370\u5ea6\u5c3c\u897f\u4e9a\",\"nation_group_id\":19,\"state_id\":2},{\"id\":62,\"name\":\"\u51b0\u5cf6\",\"name_cn\":\"\u51b0\u5c9b\",\"nation_group_id\":30,\"state_id\":3},{\"id\":63,\"name\":\"\u632a\u5a01\",\"name_cn\":\"\u632a\u5a01\",\"nation_group_id\":30,\"state_id\":3},{\"id\":64,\"name\":\"\u82ac\u862d\",\"name_cn\":\"\u82ac\u5170\",\"nation_group_id\":30,\"state_id\":3},{\"id\":65,\"name\":\"\u745e\u5178\",\"name_cn\":\"\u745e\u5178\",\"nation_group_id\":30,\"state_id\":3},{\"id\":66,\"name\":\"\u82f1\u570b\",\"name_cn\":\"\u82f1\u56fd\",\"nation_group_id\":28,\"state_id\":3},{\"id\":67,\"name\":\"\u4e39\u9ea5\",\"name_cn\":\"\u4e39\u9ea6\",\"nation_group_id\":30,\"state_id\":3},{\"id\":68,\"name\":\"\u8377\u862d\",\"name_cn\":\"\u8377\u5170\",\"nation_group_id\":29,\"state_id\":3},{\"id\":69,\"name\":\"\u5fb7\u570b\",\"name_cn\":\"\u5fb7\u56fd\",\"nation_group_id\":26,\"state_id\":3},{\"id\":70,\"name\":\"\u6377\u514b\",\"name_cn\":\"\u6377\u514b\",\"nation_group_id\":26,\"state_id\":3},{\"id\":71,\"name\":\"\u745e\u58eb\",\"name_cn\":\"\u745e\u58eb\",\"nation_group_id\":25,\"state_id\":3},{\"id\":72,\"name\":\"\u6cd5\u570b\",\"name_cn\":\"\u6cd5\u56fd\",\"nation_group_id\":25,\"state_id\":3},{\"id\":73,\"name\":\"\u5e0c\u81d8\",\"name_cn\":\"\u5e0c\u814a\",\"nation_group_id\":32,\"state_id\":3},{\"id\":74,\"name\":\"\u611b\u723e\u862d\",\"name_cn\":\"\u7231\u5c14\u5170\",\"nation_group_id\":28,\"state_id\":3},{\"id\":75,\"name\":\"\u6bd4\u5229\u6642\",\"name_cn\":\"\u6bd4\u5229\u65f6\",\"nation_group_id\":29,\"state_id\":3},{\"id\":76,\"name\":\"\u5967\u5730\u5229\",\"name_cn\":\"\u5965\u5730\u5229\",\"nation_group_id\":26,\"state_id\":3},{\"id\":77,\"name\":\"\u610f\u5927\u5229\",\"name_cn\":\"\u610f\u5927\u5229\",\"nation_group_id\":27,\"state_id\":3},{\"id\":78,\"name\":\"\u8461\u8404\u7259\",\"name_cn\":\"\u8461\u8404\u7259\",\"nation_group_id\":31,\"state_id\":3},{\"id\":79,\"name\":\"\u5308\u7259\u5229\",\"name_cn\":\"\u5308\u7259\u5229\",\"nation_group_id\":26,\"state_id\":3},{\"id\":80,\"name\":\"\u897f\u73ed\u7259\",\"name_cn\":\"\u897f\u73ed\u7259\",\"nation_group_id\":31,\"state_id\":3},{\"id\":81,\"name\":\"\u99ac\u8033\u4ed6\",\"name_cn\":\"\u9a6c\u8033\u4ed6\",\"nation_group_id\":null,\"state_id\":3},{\"id\":82,\"name\":\"\u6590\u6fdf\",\"name_cn\":\"\u6590\u6d4e\",\"nation_group_id\":null,\"state_id\":4},{\"id\":83,\"name\":\"\u65b0\u897f\u862d\",\"name_cn\":\"\u65b0\u897f\u5170\",\"nation_group_id\":null,\"state_id\":4},{\"id\":84,\"name\":\"\u6fb3\u5927\u5229\u4e9e\",\"name_cn\":\"\u6fb3\u5927\u5229\u4e9a\",\"nation_group_id\":33,\"state_id\":4},{\"id\":85,\"name\":\"\u57c3\u53ca\",\"name_cn\":\"\u57c3\u53ca\",\"nation_group_id\":null,\"state_id\":5},{\"id\":86,\"name\":\"\u5357\u975e\",\"name_cn\":\"\u5357\u975e\",\"nation_group_id\":39,\"state_id\":5},{\"id\":87,\"name\":\"\u80af\u5c3c\u4e9e\",\"name_cn\":\"\u80af\u5c3c\u4e9a\",\"nation_group_id\":null,\"state_id\":5},{\"id\":88,\"name\":\"\u6bdb\u91cc\u6c42\u65af\",\"name_cn\":\"\u6bdb\u91cc\u6c42\u65af\",\"nation_group_id\":null,\"state_id\":5},{\"id\":89,\"name\":\"\u7f8e\u570b\",\"name_cn\":\"\u7f8e\u56fd\",\"nation_group_id\":35,\"state_id\":6},{\"id\":90,\"name\":\"\u52a0\u62ff\u5927\",\"name_cn\":\"\u52a0\u62ff\u5927\",\"nation_group_id\":35,\"state_id\":6},{\"id\":91,\"name\":\"\u58a8\u897f\u54e5\",\"name_cn\":\"\u58a8\u897f\u54e5\",\"nation_group_id\":36,\"state_id\":6},{\"id\":92,\"name\":\"\u5df4\u897f\",\"name_cn\":\"\u5df4\u897f\",\"nation_group_id\":37,\"state_id\":7},{\"id\":93,\"name\":\"\u79d8\u9b6f\",\"name_cn\":\"\u79d8\u9c81\",\"nation_group_id\":37,\"state_id\":7},{\"id\":94,\"name\":\"\u667a\u5229\",\"name_cn\":\"\u667a\u5229\",\"nation_group_id\":37,\"state_id\":7},{\"id\":95,\"name\":\"\u963f\u6839\u5ef7\",\"name_cn\":\"\u963f\u6839\u5ef7\",\"nation_group_id\":37,\"state_id\":7}]";

    public Nation() {
        this(-1, "", "", -1, -1);
    }

    public Nation(int id, String name, String name_cn, int state_id, int nation_group_id) {
        this.id = id;
        this.name = name;
        this.state_id = state_id;
        this.name_cn = name_cn;
        this.nation_group_id = nation_group_id;
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

    public String getNameCn() {
        return name_cn;
    }

    public int getNationGroupId() {
        return nation_group_id;
    }

    public static ArrayList<Nation> getGroupNations(int group_id) {
        TreeMap map = new TreeMap<Integer, ArrayList<Nation>>();

        try {
            JSONArray jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {

                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                int state_id1 = jArray.getJSONObject(i).getInt("state_id");
                String name_cn = jArray.getJSONObject(i).getString("name_cn");

                int nation_group_id = 0;
                if (!jArray.getJSONObject(i).isNull("nation_group_id"))
                    nation_group_id = jArray.getJSONObject(i).getInt("nation_group_id");

                Nation ag = new Nation(id, name, name_cn, state_id1, nation_group_id);
                if (map.containsKey(ag.getNationGroupId())) {
                    ((ArrayList<Nation>) map.get(ag.getNationGroupId())).add(ag);
                } else {
                    ArrayList<Nation> newlist = new ArrayList<Nation>(10);
                    newlist.add(ag);
                    map.put(ag.getNationGroupId(), newlist);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (ArrayList<Nation>) map.get(group_id);
    }
}
