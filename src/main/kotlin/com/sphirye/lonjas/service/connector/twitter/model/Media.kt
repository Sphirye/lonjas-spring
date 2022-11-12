package com.sphirye.lonjas.service.connector.twitter.model

import com.google.gson.annotations.SerializedName

class Media (
    @SerializedName("media_key") var mediaKey: String? = null,
    var url: String? = null,
    var type: MediaType? = null,
    var variants: MutableList<Variant> = mutableListOf()
) {
    enum class MediaType {
        @SerializedName("photo") PHOTO,
        @SerializedName("video") VIDEO,
        @SerializedName("animated_gif") GIF
    }
}