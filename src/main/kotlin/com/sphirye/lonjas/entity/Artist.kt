package com.sphirye.lonjas.entity

import com.sphirye.lonjas.entity.twitter.TwitterUser
import javax.persistence.*

@Entity
class Artist(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @OneToOne @JoinColumn(name = "twitter_id", referencedColumnName = "id")
    var twitter: TwitterUser? = null,
)