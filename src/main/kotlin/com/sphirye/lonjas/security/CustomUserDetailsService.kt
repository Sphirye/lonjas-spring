package com.sphirye.lonjas.security

import com.sphirye.lonjas.entity.Authority
import com.sphirye.lonjas.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Component("userDetailsService")
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    //Login by username
//    @Transactional
//    override fun loadUserByUsername(username: String): UserDetails {
//        return userRepository.findOneWithAuthoritiesByUsername(username)
//            .map { user: com.sphirye.lonjas.entity.User -> createUser(username, user) }
//            .orElseThrow { UsernameNotFoundException("$username -> not found in database.") }
//    }

    @Transactional
    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findOneWithAuthoritiesByEmail(email)
            .map { user: com.sphirye.lonjas.entity.User -> createUser(email, user) }
            .orElseThrow { UsernameNotFoundException("$email -> not found in database.") }
    }



    private fun createUser(username: String, user: com.sphirye.lonjas.entity.User): User {
        if (!user.enabled) {
            throw RuntimeException("$username -> Is not activated.")
        }

        val grantedAuthorities = user.authorities!!.stream()
            .map { authority: Authority -> SimpleGrantedAuthority(authority.role) }
            .collect(Collectors.toList())

        return User(
            user.username,
            user.password,
            grantedAuthorities
        )
    }
}