package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.BadRequestException
import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Category
import com.sphirye.lonjas.repository.CategoryRepository
import com.sphirye.lonjas.repository.criteria.CategoryCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CategoryService {

    @Autowired lateinit var categoryRepository: CategoryRepository
    @Autowired lateinit var categoryCriteria: CategoryCriteria

    fun init() {
        if (categoryRepository.count() <= 0) {
            create("touhou")
            create("shantae")
            create("cookie_run")
        }
    }

    fun create(name: String): Category {
        if (name.isEmpty()) { throw BadRequestException("Invalid name") }
        if (existsByName(name)) { throw DuplicatedException("A category with the given name already exists") }
        val category = Category()
        category.name = name
        return categoryRepository.save(category)
    }

    fun findById(id: Long): Category {
        if (!existsById(id)) { throw NotFoundException("Category not found") }
        return categoryRepository.getReferenceById(id)
    }
    fun existsById(id: Long): Boolean { return categoryRepository.existsById(id) }

    fun existsByName(name: String): Boolean { return categoryRepository.existsByName(name) }

    fun findFilterPageable(page: Int, size: Int, search: String?): Page<Category> {
        return categoryCriteria.findFilterPageable(page, size, search)
    }

}