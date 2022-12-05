package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): Boolean
}