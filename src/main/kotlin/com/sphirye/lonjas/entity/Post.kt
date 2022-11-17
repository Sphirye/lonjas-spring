package com.sphirye.lonjas.entity

import com.sphirye.lonjas.entity.twitter.Tweet
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class Post (
    @Id var id: String?,
    @ManyToOne var artist: Artist? = null,
    @OneToOne val tweet: Tweet? = null,
    var approved: Boolean = false,
)