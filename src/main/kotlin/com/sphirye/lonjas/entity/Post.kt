package com.sphirye.lonjas.entity

import com.sphirye.lonjas.entity.twitter.Tweet
import javax.persistence.*

@Entity
class Post (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @ManyToOne
    var artist: Artist? = null,
    @OneToOne
    val tweet: Tweet? = null,
    var approved: Boolean = false,
)