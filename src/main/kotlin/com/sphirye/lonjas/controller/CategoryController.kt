package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.Category
import com.sphirye.lonjas.service.CategoryService
import com.sphirye.lonjas.service.tool.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController {

    @Autowired lateinit var categoryService: CategoryService

    @GetMapping("/public/category")
    fun findCategories(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) search: String?
    ): ResponseEntity<List<Category>> {
        val result = categoryService.findFilterPageable(page, size, search)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }

    @PostMapping("/api/category")
    fun createCategory(@RequestParam("name") name: String): ResponseEntity<Category> {
       return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(name))
    }

}