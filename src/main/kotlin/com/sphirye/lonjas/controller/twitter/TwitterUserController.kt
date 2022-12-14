package com.sphirye.lonjas.controller.twitter

import com.sphirye.lonjas.entity.twitter.TwitterUser
import com.sphirye.lonjas.service.twitter.TwitterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TwitterUserController {

    @Autowired lateinit var twitterUserService: TwitterUserService

    @PostMapping("/api/twitter/user/register/{id}")
    fun registerTwitterUser(@PathVariable id: String): ResponseEntity<TwitterUser> {
        return ResponseEntity.status(HttpStatus.CREATED).body(twitterUserService.register(id))
    }

    @PostMapping("/api/twitter/user/register/by/username/{username}")
    fun registerTwitterUserByUsername(@PathVariable username: String): ResponseEntity<TwitterUser> {
        return ResponseEntity.status(HttpStatus.CREATED).body(twitterUserService.registerByUsername(username))
    }

    @PatchMapping("/api/twitter/user/{id}/sync")
    fun syncTwitterUser(@PathVariable id: String): ResponseEntity<TwitterUser> {
        return ResponseEntity.status(HttpStatus.OK).body(twitterUserService.sync(id))
    }

}