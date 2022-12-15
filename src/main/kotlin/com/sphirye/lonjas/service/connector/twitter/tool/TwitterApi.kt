package com.sphirye.lonjas.service.connector.twitter.tool

import com.sphirye.lonjas.config.PropertyConfig
import com.sphirye.lonjas.service.connector.twitter.model.Tweet
import com.sphirye.lonjas.service.connector.twitter.model.Tweets
import com.sphirye.lonjas.service.connector.twitter.model.User
import retrofit2.Call
import retrofit2.http.*

interface TwitterApi {

    companion object {
        private val TWITTER_TOKEN: String = "Bearer ${PropertyConfig.TWITTER_TOKEN}"
    }

    @GET("/2/users/by/username/{username}")
    fun getTwitterUserByUsername(
        @Path(value = "username") username: String,
        @QueryMap params: HashMap<String, String>,
        @Header("Authorization") token: String? = TWITTER_TOKEN
    ): Call<User>

    @GET("/2/users/{id}")
    fun getTwitterUserById(
        @Path(value = "id") id: String,
        @QueryMap params: HashMap<String, String>,
        @Header("Authorization") token: String? = TWITTER_TOKEN
    ): Call<User>


    @GET("/2/users/{id}/tweets")
    fun getTweetsByUserId(
        @Path(value = "id") id: String,
        @QueryMap params: HashMap<String, String>,
        @Header("Authorization") token: String? = TWITTER_TOKEN
    ): Call<Tweets>

    @GET("/2/tweets/{id}")
    fun getTweetById(
        @Path(value= "id") id: String,
        @QueryMap params: HashMap<String, String>,
        @Header("Authorization") token: String? = TWITTER_TOKEN
    ): Call<Tweet>

}