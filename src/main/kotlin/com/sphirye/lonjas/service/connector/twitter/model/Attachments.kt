package com.sphirye.lonjas.service.connector.twitter.model

import com.google.gson.annotations.SerializedName

class Attachments(
    @SerializedName("media_keys") var mediaKeys: MutableList<String>? = mutableListOf(),
)