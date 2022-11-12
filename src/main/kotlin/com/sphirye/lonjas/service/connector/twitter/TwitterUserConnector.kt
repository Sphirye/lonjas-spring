package com.sphirye.lonjas.service.connector.twitter

import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.tool.RetrofitTool
import io.github.cdimascio.dotenv.dotenv
import org.springframework.stereotype.Component

@Component
class TwitterUserConnector (
    private val token: String = "Bearer ${dotenv()["TWITTER_BEARER_TOKEN"]}"
) {

    fun getTwitterUserByUsername(username: String): User {
        val paramsMap = hashMapOf("user.fields" to "username,profile_image_url")
        return RetrofitTool.api.getTwitterUserByUsername(username, paramsMap, token).execute().body()!!
    }

}