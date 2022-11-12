package com.sphirye.lonjas.service.tool

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sphirye.lonjas.service.connector.twitter.model.Tweet
import com.sphirye.lonjas.service.connector.twitter.model.Tweets
import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.connector.twitter.tool.TwitterApi
import com.sphirye.lonjas.service.connector.twitter.adapter.TweetAdapter
import com.sphirye.lonjas.service.connector.twitter.adapter.TweetsAdapter
import com.sphirye.lonjas.service.connector.twitter.adapter.UserAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitTool {

    var api: TwitterApi
    var gson = Gson()

    init {
        val gsonBuilder = GsonBuilder()

            // Twitter
            .registerTypeAdapter(Tweet::class.java, TweetAdapter())
            .registerTypeAdapter(Tweets::class.java, TweetsAdapter())
            .registerTypeAdapter(User::class.java, UserAdapter())

            .create()

        val gsonConverter = GsonConverterFactory.create(gsonBuilder)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitter.com")
            .addConverterFactory(gsonConverter)
            .build()

        api = retrofit.create(TwitterApi::class.java)
    }
}