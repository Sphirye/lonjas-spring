package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.twitter.Tweet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TweetRepository : JpaRepository<Tweet, Long> {

    fun getReferenceById(id: String): Tweet
    fun existsById(id: String): Boolean
    fun deleteById(id: String)

}