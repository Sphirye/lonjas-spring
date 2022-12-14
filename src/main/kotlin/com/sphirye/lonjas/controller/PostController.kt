package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.Post
import com.sphirye.lonjas.service.PostService
import com.sphirye.lonjas.service.tool.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {

    @Autowired lateinit var postService: PostService
    @GetMapping("/public/post")
    fun findPublicPosts(
        @RequestParam page: Int, @RequestParam size: Int,
        @RequestParam(required = false) search: String?,
        @RequestParam(required = false) artistId: Long?,
        @RequestParam(required = false) categoryIds: List<Long>?,
        @RequestParam(required = false) characterIds: List<Long>?,
        @RequestParam(required = false) tagIds: List<Long>?
    ): ResponseEntity<MutableList<Post>> {
        val result = postService.findFilterPageable2(page, size, search, artistId, categoryIds, characterIds, tagIds, true)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }

    @GetMapping("/api/post")
    fun findPosts(
        @RequestParam page: Int, @RequestParam size: Int,
        @RequestParam(required = false) search: String?,
        @RequestParam(required = false) artistId: Long?,
        @RequestParam(required = false) categoryIds: List<Long>?,
        @RequestParam(required = false) characterIds: List<Long>?,
        @RequestParam(required = false) tagIds: List<Long>?,
        @RequestParam(required = false) enabled: Boolean?,
    ): ResponseEntity<MutableList<Post>> {
        val result = postService.findFilterPageable2(page, size, search, artistId, categoryIds, characterIds, tagIds, enabled)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }

    @GetMapping("/public/post/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<Post> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id))
    }

    @PostMapping("/api/artist/{artistId}/post/tweet")
    fun createPostFromTweet(
        @PathVariable artistId: Long,
        @RequestParam("tweetId") tweetId: String,
        @RequestParam(required = false) tags: List<Long>?,
        @RequestParam(required = false) categories: List<Long>?,
        @RequestParam(required = false) characters: List<Long>?
    ): ResponseEntity<Post> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            postService.createFromTweet(artistId, tweetId, tags, categories, characters)
        )
    }

    @PatchMapping("/api/post/{id}",  produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePost(@PathVariable id: Long, @RequestBody request: Post): ResponseEntity<Post> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.update(id, request))
    }

    @DeleteMapping("/api/post/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.delete(id))
    }
}