package com.sphirye.lonjas.service.connector.twitter

import com.sphirye.lonjas.service.connector.twitter.model.Tweet
import com.sphirye.lonjas.service.connector.twitter.model.Tweets
import com.sphirye.lonjas.service.tool.RetrofitTool
import com.sphirye.lonjas.service.tool.RetrofitTool.gson
import io.github.cdimascio.dotenv.dotenv
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TweetConnector {

    fun getTweetById(tweetId: String): Tweet {
        val paramsMap = hashMapOf(
            "user.fields" to "username,profile_image_url",
            "expansions" to "attachments.media_keys,author_id",
            "media.fields" to "type,url,variants"
        )

        val response = RetrofitTool.api.getTweetById(tweetId, paramsMap).execute()
        return response.body()!!
    }

    fun findPageableTweetsByUserId(userId: String, nextToken: String?): Tweets {
        val paramsMap = hashMapOf(
            "exclude" to "retweets",
            "user.fields" to "username,profile_image_url",
            "expansions" to "attachments.media_keys,author_id",
            "media.fields" to "type,url,variants",
            "max_results" to "100"
        )

        if (!nextToken.isNullOrEmpty()) {
            paramsMap["pagination_token"] = nextToken
        }

        val response = RetrofitTool.api.getTweetsByUserId(userId, paramsMap).execute()
        return response.body()!!
    }

    fun findAllTweetsByUserId(userId: String): Tweets {
        var tweets: Tweets = Tweets()
        var nextToken: String? = null
        var count: Int = 0
        var finished: Boolean = false

        while (!finished) {
            val response = findPageableTweetsByUserId(userId, nextToken)
            count += 1

            response.data.forEach { tweets.data.add(it) }

            response.includes.media.forEach {
                tweets.includes.media.add(it)
            }

            nextToken = response.meta!!.nextToken
            finished = (response.meta!!.nextToken == null)
        }

        return tweets
    }

}