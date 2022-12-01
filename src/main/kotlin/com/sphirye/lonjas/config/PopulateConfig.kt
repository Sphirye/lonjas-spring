package com.sphirye.lonjas.config

import com.sphirye.lonjas.service.*
import com.sphirye.lonjas.service.twitter.TweetService
import com.sphirye.lonjas.service.twitter.TwitterService
import com.sphirye.lonjas.service.twitter.TwitterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class PopulateConfig {

    @Autowired lateinit var authorityService: AuthorityService
    @Autowired lateinit var userService: UserService
    @Autowired lateinit var twitterUserService: TwitterUserService
    @Autowired lateinit var tweetService: TweetService
    @Autowired lateinit var twitterService: TwitterService
    @Autowired lateinit var artistService: ArtistService

    //Mocked data, remove before mounting to production
    @PostConstruct
    fun initDatabase() {

        val ids = listOf(
//            "1178694351624572931",
            "1403116270908751873",
//            "1294964657841672193",
//            "873217529514082304"
        )

        authorityService.init()
        userService.init()

        ids.forEach {
            artistService.createFromTwitter(it)
            tweetService.syncUserTweets(it)
        }
    }
}