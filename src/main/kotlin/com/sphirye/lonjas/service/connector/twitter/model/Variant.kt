package com.sphirye.lonjas.service.connector.twitter.model

import com.google.gson.annotations.SerializedName

class Variant (
    @SerializedName("bit_rate") var bitRate: Int? = null,
    @SerializedName("content_type") var contentType: String? = null,
    var url: String? = null
)