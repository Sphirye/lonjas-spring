package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Post
import com.sphirye.lonjas.repository.PostRepository
import com.sphirye.lonjas.service.twitter.TweetService
import com.sphirye.lonjas.service.twitter.TwitterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService {

    @Autowired lateinit var postRepository: PostRepository
    @Autowired lateinit var tweetService: TweetService
    @Autowired lateinit var twitterUserService: TwitterUserService

    fun createFromTweet(tweetId: String) {
        val tweet = tweetService
    }

    fun findById(id: Long): Post {
        if (!existsById(id)) { throw NotFoundException("Post not found") }
        return postRepository.getReferenceById(id)
    }

    fun existsById(id: Long): Boolean { return postRepository.existsById(id) }

}