package com.sphirye.lonjas.service.twitter

import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.twitter.Tweet
import com.sphirye.lonjas.repository.TweetRepository
import com.sphirye.lonjas.repository.criteria.TweetCriteria
import com.sphirye.lonjas.service.connector.twitter.TweetConnector
import com.sphirye.lonjas.service.connector.twitter.model.Media
import com.sphirye.lonjas.service.connector.twitter.model.Variant
import com.sphirye.lonjas.service.tool.RetrofitTool.gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class TweetService {

    @Autowired lateinit var twitterUserService: TwitterUserService
    @Autowired lateinit var tweetConnector: TweetConnector
    @Autowired lateinit var tweetRepository: TweetRepository
    @Autowired lateinit var tweetCriteria: TweetCriteria

    fun syncUserTweets(id: String) {
        val user = twitterUserService.findById(id)
        val tweets = tweetConnector.findAllTweetsByUserId(id)

        println("[Iniciando sincronización] - @${user.username}")

        tweets.data.forEach {
            val tweet = Tweet()

            tweet.id = it.id
            tweet.author = user
            tweet.text = it.text

            it.attachments?.mediaKeys?.forEach { mediaKey ->
                val media = tweets.includes.media.find { m -> m.mediaKey == mediaKey }

                when (media?.type) {

                    (Media.MediaType.PHOTO) -> { tweet.images.add(media.url!!) }

                    (Media.MediaType.VIDEO) -> {
                        val variant = getHighestBitRateMedia(media.variants)
                        tweet.videos.add(variant.url!!)
                    }

                }
            }
            tweetRepository.save(tweet)
        }
        println("[Sincronización finalizada] - @${user.username}")
        return
    }

    fun findFilterPageable(page: Int, size: Int, search: String?, twitterId: String): Page<Tweet> {
        return tweetCriteria.findFilterPageable(page, size, search, twitterId)
    }
    fun findById(id: String): Tweet {
        if (!existsById(id)) { throw NotFoundException("Tweet not found") }
        return tweetRepository.getReferenceById(id)
    }
    fun existsById(id: String): Boolean { return tweetRepository.existsById(id) }

    fun getHighestBitRateMedia(variants: List<Variant>): Variant {
        var videoMedia: List<Variant> = variants.filter { v -> v.contentType == "video/mp4" }
        return videoMedia.sortedWith(compareBy { v -> v.bitRate }).last()
    }
}