package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.BadRequestException
import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Artist
import com.sphirye.lonjas.entity.Post
import com.sphirye.lonjas.entity.twitter.Tweet
import com.sphirye.lonjas.repository.PostRepository
import com.sphirye.lonjas.repository.criteria.PostCriteria
import com.sphirye.lonjas.service.twitter.TweetService
import com.sphirye.lonjas.service.twitter.TwitterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PostService {

    @Autowired lateinit var postRepository: PostRepository
    @Autowired lateinit var tweetService: TweetService
    @Autowired lateinit var twitterUserService: TwitterUserService
    @Autowired lateinit var artistService: ArtistService
    @Autowired lateinit var postCriteria: PostCriteria
    fun createFromTweet(artistId: Long, tweetId: String): Post {

        val tweet: Tweet = tweetService.findById(tweetId)
        val artist: Artist = artistService.findById(artistId)

        if (artist.twitter!!.id != tweet.author!!.id) {
            throw BadRequestException("The tweet author does not match with the given artist.")
        }

        if (existsByTweetId(tweetId)) {
            throw DuplicatedException("This tweet has been already registered.")
        }

        val post = Post()

        post.artist = artist
        post.tweet = tweet
        post.type = Post.Type.TWEET

        return postRepository.save(post)
    }

    fun findById(id: Long): Post {
        if (!existsById(id)) { throw NotFoundException("Post not found") }
        return postRepository.getReferenceById(id)
    }

    fun existsById(id: Long): Boolean { return postRepository.existsById(id) }

    fun existsByTweetId(tweetId: String): Boolean { return postRepository.existsByTweetId(tweetId) }

    fun findFilterPageable(page: Int, size: Int, artistId: Long?): Page<Post> {
        return postCriteria.findFilterPageable(page, size, artistId)
    }

}