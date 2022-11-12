package com.sphirye.lonjas.service.connector.twitter

import com.sphirye.lonjas.service.connector.twitter.model.Tweets
import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.tool.RetrofitTool
import io.github.cdimascio.dotenv.dotenv
import org.springframework.stereotype.Component
import retrofit2.Call

@Component
class TwitterUserConnector (
    private val token: String = "Bearer ${dotenv()["TWITTER_BEARER_TOKEN"]}"
) {

    fun getTwitterUserByUsername(username: String): User {
        val params = hashMapOf("user.fields" to "profile_image_url")
        val response = RetrofitTool.api.getTwitterUserByUsername(username, params, token).execute()
        return response.body()!!
    }

    fun getTwitterUserById(id: String): User {
        val params = hashMapOf("user.fields" to "profile_image_url")
        val response = RetrofitTool.api.getTwitterUserById(id, params, token).execute()
        return response.body()!!
    }

}