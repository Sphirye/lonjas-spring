package com.sphirye.lonjas.controller

import com.sphirye.lonjas.service.connector.twitter.model.Tweet
import com.sphirye.lonjas.service.connector.twitter.model.Tweets
import com.sphirye.lonjas.service.connector.twitter.model.User
import com.sphirye.lonjas.service.TwitterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TwitterController {

    @Autowired lateinit var twitterService: TwitterService

    @GetMapping("/public/twitter/user/by/username/{username}")
    fun getTwitterUserByUsername(@PathVariable username: String): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getTwitterUserByUsername(username))
    }

    @GetMapping("/public/twitter/user/{id}")
    fun getTwitterUserById(@PathVariable id: String): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getTwitterUserById(id))
    }

    @GetMapping("/public/twitter/user/{id}/tweets")
    fun getUserTweets(@PathVariable id: String): ResponseEntity<Tweets> {
        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getAllUserTweetsById(id))
    }

    @GetMapping("/public/twitter/tweet/{id}")
    fun getTweetById(@PathVariable id: String): ResponseEntity<Tweet> {
        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getTweetById(id))
    }

}