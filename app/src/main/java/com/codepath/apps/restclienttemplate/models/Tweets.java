package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweets {
    public String body;
    public String createdAt;
    public  User user;
    public static Tweets fromJson(JSONObject jsonObject) throws JSONException {
        Tweets tweets = new Tweets();
        tweets.body = jsonObject.getString("text");
        tweets.createdAt = jsonObject.getString("created_at");
        tweets.user = User.fromJson(jsonObject.getJSONObject("user"));
        return tweets;
    }

    public static List<Tweets> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweets> tweets = new ArrayList<>();
        for(int i = 0; i < jsonArray.length();i++)
        {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
