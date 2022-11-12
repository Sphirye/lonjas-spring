package com.sphirye.lonjas.controller.twitter

import com.sphirye.lonjas.entity.twitter.TwitterUser
import com.sphirye.lonjas.service.TweetService
import com.sphirye.lonjas.service.TwitterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetController {

    @Autowired lateinit var twitterUserService: TwitterUserService
    @Autowired lateinit var tweetService: TweetService

    @PatchMapping("/public/twitter/user/{id}/tweet/sync")
    fun syncUserTweets(@PathVariable id: String): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.syncUserTweets(id))
    }

}