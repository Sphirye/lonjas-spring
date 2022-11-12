package com.sphirye.lonjas.service.connector.twitter.model

import com.sphirye.lonjas.entity.User

class Includes (
    var media: MutableList<Media> = mutableListOf(),
    var users: MutableList<User> = mutableListOf()
)