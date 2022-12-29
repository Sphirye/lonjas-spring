package com.sphirye.lonjas.controller.twitter

import com.sphirye.lonjas.entity.twitter.Tweet
import com.sphirye.lonjas.service.twitter.TweetService
import com.sphirye.lonjas.service.twitter.TwitterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetController {

    @Autowired lateinit var twitterUserService: TwitterUserService
    @Autowired lateinit var tweetService: TweetService


    @GetMapping("/api/twitter/tweet/{id}")
    fun getTweet(@PathVariable id: String): ResponseEntity<Tweet> {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.findById(id))
    }

    @PatchMapping("/api/twitter/user/{id}/tweet/sync")
    fun syncUserTweets(@PathVariable id: String): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.syncUserTweets(id))
    }

    @DeleteMapping("/api/twitter/tweet/{id}")
    fun deleteTweet(@PathVariable id: String): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.deleteById(id))
    }

}