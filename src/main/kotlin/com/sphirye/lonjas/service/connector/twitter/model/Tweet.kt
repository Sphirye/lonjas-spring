package com.sphirye.lonjas.service.connector.twitter.model

import com.google.gson.annotations.SerializedName

class Tweet (
    var data: TweetData? = null,
    var includes: Includes? = null,
    var meta: Meta? = null
)

class TweetData(
    @SerializedName("author_id") var authorId: String? = null,
    var id: String? = null,
    var text: String? = null,
    var attachments: Attachments? = null
)