package com.sphirye.lonjas.service.connector.twitter.model

import com.google.gson.annotations.SerializedName

class Meta (
    @SerializedName("result_count") var resultCount: Int? = null,
    @SerializedName("newest_id") var newestId: String? = null,
    @SerializedName("oldest_id") var oldestId: String? = null,
    @SerializedName("next_token") var nextToken: String? = null,
    @SerializedName("previous_token") var previousToken: String? = null
)