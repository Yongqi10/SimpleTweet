package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.restclienttemplate.models.Tweets;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class TImeLineActivity extends AppCompatActivity {
    public static final String TAG = "TemLineActivity";

    TwitterClient client;
    List<Tweets> tweets;
    TweetsAdapter adapter;
    RecyclerView rvTweets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

         client = TwitterApplication.getRestClient(this);
         rvTweets = findViewById(R.id.rvTweets);
         tweets = new ArrayList<>();
         adapter = new TweetsAdapter(this, tweets);
         rvTweets.setLayoutManager(new LinearLayoutManager(this));
         rvTweets.setAdapter(adapter);

         populateHomeTimeline();


    }

    private void populateHomeTimeline() {
        client.getHomeTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.i(TAG,"onSuccess " + json.toString());
                JSONArray jsonArray = json.jsonArray;
                try {
                    tweets.addAll(Tweets.fromJsonArray(jsonArray));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG,"Json exception",e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(TAG,"onFailure",throwable);
            }
        });
    }
}