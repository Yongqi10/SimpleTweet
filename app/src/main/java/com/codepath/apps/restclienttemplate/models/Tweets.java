package com.codepath.apps.restclienttemplate.models;

import androidx.versionedparcelable.ParcelField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Tweets {
    public String body;
    public String createdAt;
    public  User user;
    public long id;
    public Tweets(){

    }


    public static Tweets fromJson(JSONObject jsonObject) throws JSONException {
        Tweets tweets = new Tweets();
        tweets.body = jsonObject.getString("text");
        tweets.createdAt = jsonObject.getString("created_at");
        tweets.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweets.id = jsonObject.getLong("id");
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
