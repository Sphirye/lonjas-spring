package com.sphirye.lonjas.service.connector.twitter.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sphirye.lonjas.service.connector.twitter.model.Tweets
import com.sphirye.lonjas.service.tool.RetrofitTool
import java.lang.reflect.Type

class TweetsAdapter: JsonDeserializer<Tweets> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Tweets {
        return RetrofitTool.gson.fromJson(json, Tweets::class.java)
    }

}