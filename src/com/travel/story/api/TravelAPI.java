package com.travel.story.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.travel.story.entity.Area;
import com.travel.story.entity.AreaGroup;
import com.travel.story.entity.AreaIntro;
import com.travel.story.entity.AreaIntroCategory;
import com.travel.story.entity.Nation;
import com.travel.story.entity.NationGroup;
import com.travel.story.entity.Note;
import com.travel.story.entity.Site;
import com.travel.story.entity.State;

public class TravelAPI {
    final static String         HOST  = "http://106.187.103.107";
    public static final String  TAG   = "NOVEL_API";
    public static final boolean DEBUG = true;

    public static Site getSite(int site_id) {
        String message = getMessageFromServer("GET", "/api/v1/sites/" + site_id + ".json", null, null);
        if (message == null) {
            return null;
        } else {
            return parseSite(message);
        }
    }

    public static ArrayList<Site> getNationGroupSites(int nation_group_id, int page) {
        String message = getMessageFromServer("GET", "/api/v1/sites/nation_group.json?nation_group_id=" + nation_group_id + "&page=" + page, null, null);
        ArrayList<Site> sites = new ArrayList<Site>();
        if (message == null) {
            return null;
        } else {
            return parseSites(message, sites);
        }
    }

    public static ArrayList<Site> getAreaSites(int area_id, int page) {
        String message = getMessageFromServer("GET", "/api/v1/sites.json?area_id=" + area_id + "&page=" + page, null, null);
        ArrayList<Site> sites = new ArrayList<Site>();
        if (message == null) {
            return null;
        } else {
            return parseSites(message, sites);
        }
    }

    public static ArrayList<Note> getBestNotes(int page) {
        String message = getMessageFromServer("GET", "/api/v1/notes/best_notes.json?page=" + page, null, null);
        ArrayList<Note> notes = new ArrayList<Note>();
        if (message == null) {
            return null;
        } else {
            return parseNotes(message, notes);
        }
    }

    public static ArrayList<Note> getNewNotes(int page) {
        String message = getMessageFromServer("GET", "/api/v1/notes/new_notes.json?page=" + page, null, null);
        ArrayList<Note> notes = new ArrayList<Note>();
        if (message == null) {
            return null;
        } else {
            return parseNotes(message, notes);
        }
    }

    public static ArrayList<Note> getMostViewNotes(int page) {
        String message = getMessageFromServer("GET", "/api/v1/notes/most_view_notes.json?page=" + page, null, null);
        ArrayList<Note> notes = new ArrayList<Note>();
        if (message == null) {
            return null;
        } else {
            return parseNotes(message, notes);
        }
    }

    public static Note getNote(int note_id) {
        String message = getMessageFromServer("GET", "/api/v1/notes/" + note_id + ".json", null, null);
        if (message == null) {
            return null;
        } else {
            return parseNote(message);
        }
    }

    // oder: 1=>best,2=>new, 3=> read num
    public static ArrayList<Note> getAreaNotes(int area_id, int page, int order) {
        String message = getMessageFromServer("GET", "/api/v1/notes.json?area_id=" + area_id + "&order=" + order + "&page=" + page, null, null);
        ArrayList<Note> notes = new ArrayList<Note>();
        if (message == null) {
            return null;
        } else {
            return parseNotes(message, notes);
        }
    }

    // oder: 1=>best,2=>new, 3=> read num
    public static ArrayList<Note> getNationGroupNotes(int nation_group_id, int page, int order) {
        String message = getMessageFromServer("GET",
                "/api/v1/notes/nation_group.json?nation_group_id=" + nation_group_id + "&order=" + order + "&page=" + page, null, null);
        ArrayList<Note> notes = new ArrayList<Note>();
        if (message == null) {
            return null;
        } else {
            return parseNotes(message, notes);
        }
    }

    public static ArrayList<Area> getNationAreas(int nation_id) {
        String message = getMessageFromServer("GET", "/api/v1/areas.json?nation_id=" + nation_id, null, null);
        ArrayList<Area> areas = new ArrayList<Area>();
        if (message == null) {
            return null;
        } else {
            return parseAreas(message, areas);
        }
    }

    public static ArrayList<Area> getGroupAreas(int group_id) {
        String message = getMessageFromServer("GET", "/api/v1/areas/group_areas.json?city_group_id=" + group_id, null, null);
        ArrayList<Area> areas = new ArrayList<Area>();
        if (message == null) {
            return null;
        } else {
            return parseAreas(message, areas);
        }
    }

    public static ArrayList<AreaIntro> getAreaIntros(int area_id) {
        String message = getMessageFromServer("GET", "/api/v1/area_intros.json?area_id=" + area_id, null, null);
        ArrayList<AreaIntro> intros = new ArrayList<AreaIntro>();
        if (message == null) {
            return null;
        } else {
            return parseAreaIntros(message, intros);
        }
    }

    public static AreaIntro getAreaIntro(int intro_id) {
        String message = getMessageFromServer("GET", "/api/v1/area_intros/" + intro_id + ".json", null, null);
        if (message == null) {
            return null;
        } else {
            return parseAreaIntro(message);
        }
    }

    public static String getAreaIntroCategoryName(int id) {
        return AreaIntroCategory.getCategoryName(id);
    }

    public static ArrayList<AreaIntroCategory> getAreaIntroCategories() {
        return AreaIntroCategory.getAreaIntroCategories();
    }

    public static ArrayList<Nation> getGroupNations(int group_id) {
        return Nation.getGroupNations(group_id);
    }

    public static ArrayList<NationGroup> getStateNationGroups(int state_id) {
        return NationGroup.getSteteNationGroups(state_id);
    }

    public static ArrayList<AreaGroup> getAreaGroups(int group_id) {
        return AreaGroup.getAreaGroups(group_id);
    }

    public static ArrayList<State> getStates() {
        return State.getStates();
    }

    private static String getMessageFromServer(String requestMethod, String apiPath, JSONObject json, String apiUrl) {
        URL url;
        try {
            if (apiUrl != null)
                url = new URL(apiUrl);
            else
                url = new URL(HOST + apiPath);

            if (DEBUG)
                Log.d(TAG, "URL: " + url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);

            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            if (requestMethod.equalsIgnoreCase("POST"))
                connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();

            if (requestMethod.equalsIgnoreCase("POST")) {
                OutputStream outputStream;

                outputStream = connection.getOutputStream();
                if (DEBUG)
                    Log.d("post message", json.toString());

                outputStream.write(json.toString().getBytes());
                outputStream.flush();
                outputStream.close();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder lines = new StringBuilder();
            ;
            String tempStr;

            while ((tempStr = reader.readLine()) != null) {
                lines = lines.append(tempStr);
            }
            if (DEBUG)
                Log.d("MOVIE_API", lines.toString());

            reader.close();
            connection.disconnect();

            return lines.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<AreaIntro> parseAreaIntros(String message, ArrayList<AreaIntro> intros) {
        try {
            JSONArray jArray;
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {

                int id = jArray.getJSONObject(i).getInt("id");
                int area_intro_cate_id = jArray.getJSONObject(i).getInt("area_intro_cate_id");
                String title = jArray.getJSONObject(i).getString("title");
                AreaIntro intro = new AreaIntro(id, null, title, area_intro_cate_id);
                intros.add(intro);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return intros;
    }

    private static AreaIntro parseAreaIntro(String message) {
        if (message == null) {
            return null;
        } else {

            try {
                JSONObject nObject;
                nObject = new JSONObject(message.toString());

                int id = nObject.getInt("id");
                int area_intro_cate_id = nObject.getInt("area_intro_cate_id");
                String title = nObject.getString("title");
                String intro = nObject.getString("intro");
                return new AreaIntro(id, intro, title, area_intro_cate_id);

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static ArrayList<Area> parseAreas(String message, ArrayList<Area> areas) {
        try {
            JSONArray jArray;
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {

                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                String name_cn = jArray.getJSONObject(i).getString("name_cn");
                String pic = jArray.getJSONObject(i).getString("pic");
                Area area = new Area(id, name, name_cn, pic);
                areas.add(area);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return areas;
    }

    private static ArrayList<Note> parseNotes(String message, ArrayList<Note> notes) {
        try {
            JSONArray jArray;
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {

                int id = jArray.getJSONObject(i).getInt("id");
                String author = jArray.getJSONObject(i).getString("author");
                String date = jArray.getJSONObject(i).getString("date");
                String pic = jArray.getJSONObject(i).getString("pic");
                String title = jArray.getJSONObject(i).getString("title");
                int read_num = jArray.getJSONObject(i).getInt("read_num");
                Note note = new Note(id, title, author, date, pic, null, read_num);
                notes.add(note);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return notes;
    }

    private static Note parseNote(String message) {
        if (message == null) {
            return null;
        } else {

            try {
                JSONObject nObject;
                nObject = new JSONObject(message.toString());

                int id = nObject.getInt("id");
                String author = nObject.getString("author");
                String date = nObject.getString("date");
                String pic = nObject.getString("pic");
                String title = nObject.getString("title");
                int read_num = nObject.getInt("read_num");
                String content = nObject.getString("content");

                return new Note(id, title, author, date, pic, content, read_num);

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static ArrayList<Site> parseSites(String message, ArrayList<Site> sites) {
        try {
            JSONArray jArray;
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {

                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                String pic = jArray.getJSONObject(i).getString("pic");
                int rank = -1;

                if (!jArray.getJSONObject(i).isNull("rank"))
                    rank = jArray.getJSONObject(i).getInt("rank");

                Site site = new Site(id, rank, name, pic, null, null, null);
                sites.add(site);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return sites;
    }

    private static Site parseSite(String message) {

        if (message == null) {
            return null;
        } else {

            try {
                JSONObject nObject;
                nObject = new JSONObject(message.toString());

                int id = nObject.getInt("id");
                String name = nObject.getString("name");
                String pic = nObject.getString("pic");
                int rank = -1;
                if (!nObject.isNull("rank"))
                    rank = nObject.getInt("rank");
                String info = nObject.getString("info");
                String intro = nObject.getString("intro");
                String[] pics = new String[] {};

                return new Site(id, rank, name, pic, info, intro, pics);

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
