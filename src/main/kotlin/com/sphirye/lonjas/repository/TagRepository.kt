package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : JpaRepository<Tag, Long> {

    fun findByName(name: String): Tag
    fun existsByName(name: String): Boolean

}