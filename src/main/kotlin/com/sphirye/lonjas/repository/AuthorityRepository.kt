package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository : JpaRepository<Authority?, String?> {

    fun existsByRole(role: String): Boolean
    fun findByRole(role: String): Authority

}