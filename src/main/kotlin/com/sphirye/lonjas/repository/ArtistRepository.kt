package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.Artist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArtistRepository: JpaRepository<Artist, Long> {

    fun existsByTwitter_id(id: String): Boolean

    fun findByTwitter_Id(id: String): Artist

}