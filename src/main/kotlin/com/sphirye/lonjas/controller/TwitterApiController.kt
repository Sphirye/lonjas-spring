package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.twitter.Tweet
import com.sphirye.lonjas.entity.twitter.TwitterUser
import com.sphirye.lonjas.service.twitter.TweetService
import com.sphirye.lonjas.service.twitter.TwitterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TwitterApiController {

    @Autowired lateinit var twitterUserService: TwitterUserService
    @Autowired lateinit var tweetService: TweetService

    //TODO: Replace all of this for the stored tweets in repository instead of requesting them

    @GetMapping("/api/twitter-api/user/by/username/{username}")
    fun getTwitterUserByUsername(@PathVariable username: String): ResponseEntity<TwitterUser> {
        return ResponseEntity.status(HttpStatus.OK).body(twitterUserService.retrieveByUsername(username))
    }
//
//    @GetMapping("/api/twitter/user/{id}")
//    fun getTwitterUserById(@PathVariable id: String): ResponseEntity<TwitterUser> {
//        return ResponseEntity.status(HttpStatus.OK).body(twitterUserService.findById(id))
//    }
//
//    @GetMapping("/api/twitter/tweet/{id}")
//    fun getTweetById(@PathVariable id: String): ResponseEntity<Tweet> {
//        return ResponseEntity.status(HttpStatus.OK).body(tweetService.findById(id))
//    }


//    @GetMapping("/public/twitter-api/user/by/username/{username}")
//    fun getTwitterUserByUsername(@PathVariable username: String): ResponseEntity<User> {
//        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getTwitterUserByUsername(username))
//    }
//
//    @GetMapping("/public/twitter-api/user/{id}")
//    fun getTwitterUserById(@PathVariable id: String): ResponseEntity<User> {
//        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getTwitterUserById(id))
//    }
//
//    @GetMapping("/public/twitter-api/user/{id}/tweets")
//    fun getUserTweets(@PathVariable id: String): ResponseEntity<Tweets> {
//        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getAllUserTweetsById(id))
//    }
//    @GetMapping("/public/twitter-api/tweet/{id}")
//    fun getTweetById(@PathVariable id: String): ResponseEntity<Tweet> {
//        return ResponseEntity.status(HttpStatus.OK).body(twitterService.getTweetById(id))
//    }

}