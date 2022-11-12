package com.sphirye.lonjas.service.connector.twitter.model

class Tweets (
    var data: MutableList<TweetData> = mutableListOf(),
    var includes: Includes = Includes(),
    var meta: Meta? = null
)