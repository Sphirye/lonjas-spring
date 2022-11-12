package com.sphirye.lonjas.entity.twitter

import javax.persistence.*

@Entity
class Tweet (
    @Id var id: String? = null,
    var text: String? = null,
    @ManyToOne var author: TwitterUser? = null,

    @ElementCollection @CollectionTable(name="tweetImages")
    var images: List<String>? = null,

    @ElementCollection @CollectionTable(name="tweetVideos")
    var videos: List<String>? = null,

)