package com.sphirye.lonjas.service.connector.twitter


import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.tool.RetrofitTool
import org.springframework.stereotype.Component

@Component
class TwitterUserConnector {

    fun getTwitterUserByUsername(username: String): User {
        val params = hashMapOf("user.fields" to "profile_image_url,description")
        val response = RetrofitTool.api.getTwitterUserByUsername(username, params).execute()
        return response.body()!!
    }

    fun getTwitterUserById(id: String): User {
        val params = hashMapOf("user.fields" to "profile_image_url,description")
        val response = RetrofitTool.api.getTwitterUserById(id, params).execute()
        return response.body()!!
    }

}