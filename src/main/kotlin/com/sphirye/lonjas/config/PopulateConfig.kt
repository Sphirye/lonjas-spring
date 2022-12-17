package com.sphirye.lonjas.config

import com.sphirye.lonjas.service.*
import com.sphirye.lonjas.service.twitter.TweetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class PopulateConfig {

    @Autowired lateinit var authorityService: AuthorityService
    @Autowired lateinit var userService: UserService
    @Autowired lateinit var tweetService: TweetService
    @Autowired lateinit var artistService: ArtistService
    @Autowired lateinit var tagService: TagService
    @Autowired lateinit var categoryService: CategoryService
    @Autowired lateinit var characterService: CharacterService
    @Autowired lateinit var propertyConfig: PropertyConfig
    @Value("\${spring.datasource.url}") lateinit var url: String

    //Mocked data, remove before mounting to production
    @PostConstruct
    fun initDatabase() {

        propertyConfig.init()

        println("Apuntando a: ${url}")
        println(PropertyConfig.TWITTER_TOKEN)

        val ids = listOf(
            "1178694351624572931"
        )

        authorityService.init()
        userService.init()
        categoryService.init()
        characterService.init()
        tagService.init()

        ids.forEach {
            artistService.createFromTwitter(it)
            tweetService.syncUserTweets(it)
        }
    }
}