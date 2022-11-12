package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.twitter.TwitterUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TwitterUserRepository : JpaRepository<TwitterUser, Long> {

    fun findById(id: String): TwitterUser
    fun existsById(id: String): Boolean

}