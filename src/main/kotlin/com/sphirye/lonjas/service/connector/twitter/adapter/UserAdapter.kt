package com.sphirye.lonjas.service.connector.twitter.adapter

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sphirye.lonjas.config.exception.NullResponseException
import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.tool.RetrofitTool
import java.lang.reflect.Type

class UserAdapter: JsonDeserializer<User> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): User {
        val data = RetrofitTool.gson.fromJson(json, User::class.java)

        if (data.data == null) {
            throw NullResponseException("Null Response on retrieved Twitter User")
        }

        return data
    }

}