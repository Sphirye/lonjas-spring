package com.sphirye.lonjas.entity

import com.sphirye.lonjas.entity.twitter.Tweet
import javax.persistence.*

@Entity
class Post (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne
    var artist: Artist? = null,
    @OneToOne
    var tweet: Tweet? = null,
    var approved: Boolean = false,
    var type: Type? = null,

    @ManyToMany
    @JoinTable(
        name = "rel_tag_post",
        joinColumns = [JoinColumn(name = "post_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "post_tag_id", referencedColumnName = "id")]
    )
    var tags: MutableSet<Tag> = mutableSetOf()
) {
    enum class Type { TWEET }
}