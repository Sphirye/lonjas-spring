package com.sphirye.lonjas.repository

import com.sphirye.lonjas.entity.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User
    fun existsByUsername(username: String): Boolean

    fun findByEmail(email: String): User
    fun existsByEmail(email: String): Boolean

    @EntityGraph(attributePaths = ["authorities"])
    fun findOneWithAuthoritiesByUsername(username: String): Optional<User>

    @EntityGraph(attributePaths = ["authorities"])
    fun findOneWithAuthoritiesByEmail(email: String): Optional<User>
}