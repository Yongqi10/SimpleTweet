package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.versionedparcelable.ParcelField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
@Entity(foreignKeys = @ForeignKey(entity=User.class, parentColumns="id", childColumns="userid"))
public class Tweets {
    @ColumnInfo
    @PrimaryKey
    public long id;
    @ColumnInfo
    public String body;
    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public long userid;

    @Ignore
    public  User user;

    public Tweets(){

    }


    public static Tweets fromJson(JSONObject jsonObject) throws JSONException {
        Tweets tweets = new Tweets();
        tweets.body = jsonObject.getString("text");
        tweets.createdAt = jsonObject.getString("created_at");
       User user =  tweets.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweets.id = jsonObject.getLong("id");
        tweets.userid = user.id;
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
