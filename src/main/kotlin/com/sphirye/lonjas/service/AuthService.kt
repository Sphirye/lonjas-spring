package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.BadCredentialsException
import com.sphirye.lonjas.entity.User
import com.sphirye.lonjas.security.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
@Service
class AuthService {

    @Autowired lateinit var tokenProvider: TokenProvider
    @Autowired lateinit var authenticationManagerBuilder: AuthenticationManagerBuilder
    @Autowired lateinit var passwordEncoder: PasswordEncoder

    fun login(username: String, password: String): String {
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        val authentication: Authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        return tokenProvider.createToken(authentication)
    }

    fun matchPasswords(user: User, password: String) {
        if (!passwordEncoder.matches(password, user.password)) { throw BadCredentialsException("Bad credentials") }
    }

}