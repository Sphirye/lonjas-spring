package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Character
import com.sphirye.lonjas.repository.CharacterRepository
import com.sphirye.lonjas.repository.criteria.CategoryCriteria
import com.sphirye.lonjas.repository.criteria.CharacterCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CharacterService {

    @Autowired lateinit var characterRepository: CharacterRepository
    @Autowired lateinit var categoryService: CategoryService
    @Autowired lateinit var characterCriteria: CharacterCriteria

    fun init() {
        if (characterRepository.count() <= 0) {
            create("remilia_scarlet", 1)
            create("sakuya_izayoi", 1)
            create("shantae", 2)
            create("sky", 2)
            create("starfruit_cookie", 3)
            create("cocoa_cookie", 3)
        }
    }

    fun create(name: String, categoryId: Long): Character {
        val category = categoryService.findById(categoryId)
        if (characterRepository.existsByNameAndCategoryName(name, category.name!!)) {
            throw DuplicatedException("A character with this name already exists in this category")
        }

        val character = Character()

        character.name = name
        character.category = category

        return characterRepository.save(character)
    }

    fun findById(id: Long): Character {
        if (!existsById(id)) { throw NotFoundException("Character not found") }
        return characterRepository.getReferenceById(id)
    }

    fun existsById(id: Long): Boolean { return characterRepository.existsById(id) }

    fun findFilterPageable(page: Int, size: Int, search: String?): Page<Character> {
        return characterCriteria.findFilterPageable(page, size, search)
    }



}