package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Authority
import com.sphirye.lonjas.repository.AuthorityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorityService {

    @Autowired lateinit var authorityRepository: AuthorityRepository

    fun init() {
        if (authorityRepository.count() <= 0) {
            create("ADMIN")
            create("MOD")
            create("USER")
        }
    }

    fun create(role: String): Authority {
        if (existsByRole(role)) { throw DuplicatedException("$role role already exists") }
        val authority = Authority(role)
        return authorityRepository.save(authority)
    }

    fun findByRole(role: String): Authority {
        if (!existsByRole(role)) { throw NotFoundException("$role role does not exists") }
        return authorityRepository.findByRole(role)
    }

    fun existsByRole(role: String): Boolean {
        return authorityRepository.existsByRole(role)
    }
}