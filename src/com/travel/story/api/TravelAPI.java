package com.travel.story.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

import android.util.Log;

import com.travel.story.entity.AreaGroup;
import com.travel.story.entity.AreaIntroCategory;
import com.travel.story.entity.Nation;
import com.travel.story.entity.NationGroup;
import com.travel.story.entity.State;

public class TravelAPI {
    final static String         HOST  = "http://106.187.40.42";
    public static final String  TAG   = "NOVEL_API";
    public static final boolean DEBUG = true;

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

    private static String getMessageFromServer(String requestMethod, String apiPath, JSONObject json) {
        URL url;
        try {
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
}
