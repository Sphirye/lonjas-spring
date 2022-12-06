package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.Character
import com.sphirye.lonjas.service.CharacterService
import com.sphirye.lonjas.service.tool.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CharacterController {

    @Autowired lateinit var characterService: CharacterService

    @GetMapping("/public/character")
    fun findArtists(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) search: String?,
    ): ResponseEntity<List<Character>> {
        val result = characterService.findFilterPageable(page, size, search)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }
    @PostMapping("/api/character")
    fun createCharacter(@RequestParam("name") name: String, @RequestParam("categoryId") categoryId: Long): ResponseEntity<Character> {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.create(name, categoryId))
    }



}