package com.codepath.apps.restclienttemplate.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TweetDao {
    @Query("SELECT Tweets.body AS tweet_body, Tweets.createdAt AS tweet_createdAt, Tweets.id AS tweet_id, User.*"+
            " FROM Tweets INNER JOIN User ON Tweets.userid = User.id ORDER BY Tweets.createdAt DESC LIMIT 5")
    List<TweetWithUser> recentItem();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Tweets... tweets);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(User... users);
}
