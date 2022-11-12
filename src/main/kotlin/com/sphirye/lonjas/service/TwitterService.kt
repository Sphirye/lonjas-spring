package com.sphirye.lonjas.service

import com.sphirye.lonjas.service.connector.twitter.TweetConnector
import com.sphirye.lonjas.service.connector.twitter.TwitterUserConnector
import com.sphirye.lonjas.service.connector.twitter.model.Tweet
import com.sphirye.lonjas.service.connector.twitter.model.Tweets
import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.tool.RetrofitTool
import io.github.cdimascio.dotenv.dotenv
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TwitterService (
    private val token: String = "Bearer ${dotenv()["TWITTER_BEARER_TOKEN"]}"
) {

    @Autowired lateinit var tweetConnector: TweetConnector
    @Autowired lateinit var twitterUserConnector: TwitterUserConnector

    fun getTwitterUserById(id: String) {

    }

    fun getTwitterUserByUsername(username: String): User {
        return twitterUserConnector.getTwitterUserByUsername(username)
    }

    fun getAllUserTweetsById(userId: String): Tweets {
        return tweetConnector.findAllTweetsByUserId(userId)
    }

    fun getTweetById(tweetId: String): Tweet {
        return tweetConnector.getTweetById(tweetId)
    }

}