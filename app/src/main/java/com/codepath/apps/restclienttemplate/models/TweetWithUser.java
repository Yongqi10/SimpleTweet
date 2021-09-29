package com.codepath.apps.restclienttemplate.models;

import androidx.room.Embedded;

import java.util.ArrayList;
import java.util.List;

public class TweetWithUser {
    @Embedded
    User user;

    @Embedded(prefix = "tweet_")
    Tweets tweet;

    public static List<Tweets> getTweetList(List<TweetWithUser> tweetWithUsers) {
        List<Tweets> tweets = new ArrayList<>();
        for(int i = 0; i < tweetWithUsers.size();i++)
        {
            Tweets tweet = tweetWithUsers.get(i).tweet;
            tweet.user = tweetWithUsers.get(i).user;
            tweets.add(tweet);
        }
        return tweets;
    }
}
