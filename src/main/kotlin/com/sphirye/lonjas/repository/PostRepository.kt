package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.Post
import org.springframework.data.jpa.repository.JpaRepository


interface PostRepository : JpaRepository<Post, String> {

    fun getReferenceById(id: Long): Post
    fun existsById(id: Long): Boolean

}