package com.sphirye.lonjas.config

import com.sphirye.lonjas.config.exception.*
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@Configuration
@ControllerAdvice(annotations = [RestController::class])
class ExceptionConfig {

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(e: Exception): ResponseEntity<*>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<Any>(MessageError(e.message))
    }

    @ExceptionHandler(DuplicatedException::class)
    fun duplicatedException(e: Exception): ResponseEntity<*>? {
        return ResponseEntity.status(HttpStatus.CONFLICT).body<Any>(MessageError(e.message))
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun badCredentialsException(e: Exception): ResponseEntity<*>? {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body<Any>(MessageError(e.message))
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(e: Exception): ResponseEntity<*>? {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body<Any>(MessageError(e.message))
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedException(e: Exception): ResponseEntity<*>? {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body<Any>(MessageError(e.message))
    }

    @ExceptionHandler(NullResponseException::class)
    fun nullResponseException(e: Exception): ResponseEntity<*>? {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<Any>(MessageError(e.message))
    }

    @ExceptionHandler(ConflictException::class)
    fun conflictException(e: Exception): ResponseEntity<*>? {
        return ResponseEntity.status(HttpStatus.CONFLICT).body<Any>(MessageError(e.message))
    }
}