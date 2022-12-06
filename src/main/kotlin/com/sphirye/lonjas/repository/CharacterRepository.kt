package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository : JpaRepository<Character, Long> {

    fun existsByNameAndCategoryName(name: String, categoryName: String): Boolean

}