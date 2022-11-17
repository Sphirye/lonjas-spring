package com.sphirye.lonjas.entity.twitter

import javax.persistence.*

@Entity
class Tweet (
    @Id var id: String? = null,

    @Column(length = 350)
    var text: String? = null,
    @ManyToOne var author: TwitterUser? = null,

    @ElementCollection @CollectionTable(name="tweetImages")
    var images: MutableList<String> = mutableListOf(),

    @ElementCollection @CollectionTable(name="tweetVideos")
    var videos: MutableList<String> = mutableListOf(),

    )