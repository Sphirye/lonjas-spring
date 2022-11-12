package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.model.LoginResponse
import com.sphirye.lonjas.service.AuthService
import com.sphirye.lonjas.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @Autowired lateinit var  authService: AuthService
    @Autowired lateinit var userService: UserService

    @PostMapping("/public/auth/login")
    fun login(@RequestParam("username") username: String, @RequestParam("password") password: String): ResponseEntity<LoginResponse> {
        val user = userService.findByUsername(username)
        authService.matchPasswords(user, password)
        return ResponseEntity.status(HttpStatus.OK).body(
            LoginResponse(authService.login(username, password), user, user.authorities)
        )
    }
}