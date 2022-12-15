package com.sphirye.lonjas.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PropertyConfig {

    companion object {
        lateinit var TWITTER_TOKEN: String
    }

    @Value("\${custom.twitter-token}") lateinit var twitterToken: String

    fun init() {
        TWITTER_TOKEN = twitterToken
    }
}