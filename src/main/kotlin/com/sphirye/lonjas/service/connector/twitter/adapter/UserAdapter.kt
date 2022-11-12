package com.sphirye.lonjas.service.connector.twitter.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.tool.RetrofitTool
import java.lang.reflect.Type

class UserAdapter: JsonDeserializer<User> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): User {
        return RetrofitTool.gson.fromJson(json, User::class.java)
    }

}