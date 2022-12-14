package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.Tag
import com.sphirye.lonjas.service.TagService
import com.sphirye.lonjas.service.tool.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TagController {

    @Autowired lateinit var tagService: TagService

    @GetMapping("/api/tag/{id}")
    fun getTag(@PathVariable id: Long): ResponseEntity<Tag> {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.findById(id))
    }

    @GetMapping("/public/tag")
    fun findPublicTags(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) search: String?,
    ): ResponseEntity<MutableList<Tag>> {
        val result = tagService.findPublicFilterPageable(page, size, search)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }

    @GetMapping("/api/tag")
    fun findTags(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) search: String?,
        @RequestParam(required = false) enabled: Boolean?,
    ): ResponseEntity<MutableList<Tag>> {
        val result = tagService.findFilterPageable(page, size, search, enabled)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }


    @PostMapping("/api/tag")
    fun createTag(@RequestParam name: String): ResponseEntity<Tag> {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.create(name))
    }

    @DeleteMapping("/api/tag/{id}")
    fun deleteTag(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.delete(id))
    }
}