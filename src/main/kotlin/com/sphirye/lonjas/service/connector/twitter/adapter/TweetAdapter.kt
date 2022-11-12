package com.sphirye.lonjas.service.connector.twitter.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sphirye.lonjas.service.connector.twitter.model.Tweet
import com.sphirye.lonjas.service.tool.RetrofitTool
import java.lang.reflect.Type


class TweetAdapter: JsonDeserializer<Tweet> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Tweet {
        println(json)
        return RetrofitTool.gson.fromJson(json, Tweet::class.java)
    }

}