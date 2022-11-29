package com.sphirye.lonjas.entity.twitter

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class TwitterUser (
    @Id var id: String? = null,
    var name: String? = null,
    var username: String? = null,
    var profileImageUrl: String? = null,
    var description: String? = null,
    @OneToMany @JsonIgnore
    var tweets: MutableList<Tweet> = mutableListOf()
)