package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.Post
import com.sphirye.lonjas.service.PostService
import com.sphirye.lonjas.service.tool.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {

    @Autowired lateinit var postService: PostService

    @GetMapping("/public/artist/{artistId}/post")
    fun findPostsByArtist(
        @PathVariable artistId: Long,
        @RequestParam page: Int,
        @RequestParam size: Int,
    ): ResponseEntity<List<Post>> {
        val result = postService.findFilterPageable(page, size, artistId)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }

    @PostMapping("/api/artist/{artistId}/post/tweet")
    fun createPostFromTweet(
        @PathVariable artistId: Long,
        @RequestParam("tweetId") tweetId: String,
        @RequestParam(required = false) tags: List<Long>?,
        @RequestParam(required = false) categories: List<Long>?,
        @RequestParam(required = false) characters: List<Long>?
    ): ResponseEntity<Post> {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createFromTweet(artistId, tweetId, tags, categories, characters))
    }
}