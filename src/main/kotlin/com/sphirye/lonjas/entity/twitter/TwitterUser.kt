package com.sphirye.lonjas.entity.twitter

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class TwitterUser (
    @Id var id: String? = null,
    var name: String? = null,
    var username: String? = null,
    var profileImageUrl: String? = null,
    @OneToMany var tweets: MutableList<Tweet> = mutableListOf()
)