package com.sphirye.lonjas.service.connector.twitter.model

import com.google.gson.annotations.SerializedName

class User(
    var data: UserData? = null
)
class UserData(
    var id: String? = null,
    var name: String? = null,
    var username: String? = null,
    var description: String? = null,
    @SerializedName("profile_image_url") var profileImageUrl: String? = null,
)