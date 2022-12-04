package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Tag
import com.sphirye.lonjas.repository.TagRepository
import com.sphirye.lonjas.repository.criteria.TagCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service

@Service
class TagService {

    @Autowired lateinit var tagRepository: TagRepository
    @Autowired lateinit var tagCriteria: TagCriteria

    fun init() {
        if (tagRepository.count() <= 0) {
            for (i in 1..10) { create("tag_$i") }
        }
    }

    fun create(name: String): Tag {
        if (existsByName(name)) { throw DuplicatedException("Theres already a tag with the given name.") }
        val tag = Tag()
        tag.name = name
        return tagRepository.save(tag)
    }

    fun existsByName(name: String): Boolean { return tagRepository.existsByName(name) }

    fun findById(id: Long): Tag {
        if (!existsById(id)) { throw NotFoundException("Tag not found") }
        return tagRepository.getReferenceById(id)
    }

    fun existsById(id: Long): Boolean { return tagRepository.existsById(id) }

    fun delete(id: Long) {
        val tag = findById(id)
        return tagRepository.delete(tag)
    }

    fun findFilterPageable(page: Int, size: Int, search: String?): Page<Tag> {
        return tagCriteria.findFilterPageable(page, size, search)
    }

}