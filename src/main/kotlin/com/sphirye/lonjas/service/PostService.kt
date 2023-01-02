package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.BadRequestException
import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Artist
import com.sphirye.lonjas.entity.Post
import com.sphirye.lonjas.entity.twitter.Tweet
import com.sphirye.lonjas.repository.PostRepository
import com.sphirye.lonjas.repository.criteria.PostCriteria
import com.sphirye.lonjas.service.tool.RetrofitTool.gson
import com.sphirye.lonjas.service.twitter.TweetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class PostService {

    @Autowired lateinit var postRepository: PostRepository
    @Autowired lateinit var tweetService: TweetService
    @Autowired lateinit var tagService: TagService
    @Autowired lateinit var artistService: ArtistService
    @Autowired lateinit var postCriteria: PostCriteria
    @Autowired lateinit var categoryService: CategoryService
    @Autowired lateinit var characterService: CharacterService

    fun createFromTweet(artistId: Long, tweetId: String, tags: List<Long>?, categories: List<Long>?, characters: List<Long>?): Post {

        val tweet: Tweet = tweetService.findById(tweetId)
        val artist: Artist = artistService.findById(artistId)

        if (artist.twitter!!.id != tweet.author!!.id) throw BadRequestException("The tweet author does not match with the given artist.")
        if (existsByTweetId(tweetId)) throw DuplicatedException("This tweet has been already registered.")

        val post = Post()

        post.artist = artist
        post.tweet = tweet
        post.type = Post.Type.TWEET

        tags?.forEach {
            val tag = tagService.findById(it)
            post.tags.add(tag)
        }

        categories?.forEach {
            val category = categoryService.findById(it)
            post.categories.add(category)
        }

        characters?.forEach {
            val character = characterService.findById(it)
            if (!post.categories.contains(character.category)) {
                throw BadRequestException("Category: ${character.category!!.name} is not present in the post for the Character: ${character.name}")
            }
            post.characters.add(character)
        }

        return postRepository.save(post)
    }

    @Transactional
    fun update(id: Long, request: Post): Post {
        val post = findById(id)

        post.tags.clear()
        post.categories.clear()
        post.characters.clear()

        request.categories.forEach {
            val category = categoryService.findById(it.id!!)
            if (!post.categories.contains(category)) { post.categories.add(category) }
        }

        request.characters.forEach {
            val character = characterService.findById(it.id!!)

            if (!post.categories.contains(character.category)) {
                throw BadRequestException("Category: ${character.category!!.name} is not present in the post for the Character: ${character.name}")
            }

            if (!post.characters.contains(character)) { post.characters.add(character) }
        }

        request.tags.forEach {
            val tag = tagService.findById(it.id!!)
            if (!post.tags.contains(tag)) { post.tags.add(tag) }
        }

        return postRepository.save(post)
    }

    fun relateTag(id: Long, tagId: Long) {
        val post = findById(id)
        val tag = tagService.findById(tagId)

        post.tags.add(tag)
    }

    fun unrelateTag(id: Long, tagId: Long) {
        val post = findById(id)
        val tag = tagService.findById(tagId)

        post.tags.remove(tag)
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

    fun delete(id: Long) {
        val post = findById(id)
        return postRepository.delete(post)
    }

}