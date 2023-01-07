package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Tag
import com.sphirye.lonjas.repository.PostRepository
import com.sphirye.lonjas.repository.TagRepository
import com.sphirye.lonjas.repository.criteria.TagCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class TagService {

    @Autowired lateinit var tagRepository: TagRepository
    @Autowired lateinit var tagCriteria: TagCriteria
    @Autowired lateinit var postRepository: PostRepository

    fun init() {
        if (tagRepository.count() <= 0) {
            for (i in 1..100) { create("tag_$i") }
        }
    }

    fun create(name: String): Tag {
        if (existsByName(name)) { throw DuplicatedException("Theres already a tag with the given name.") }
        val tag = Tag()
        tag.name = name
        tag.enabled = true
        return tagRepository.save(tag)
    }

    fun update(id: Long, request: Tag): Tag {
        val tag = findById(id)
        request.name?.let { tag.name = request.name }
        request.enabled?.let { tag.enabled = request.enabled }

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

        if (postRepository.countByTags_Id(tag.id!!) == 0L) {
            tagRepository.delete(tag)
        } else {
            tag.enabled = false
            tagRepository.save(tag)
        }
    }

    fun findFilterPageable(page: Int, size: Int, search: String?, enabled: Boolean?): Page<Tag> {
        return tagCriteria.findFilterPageable(page, size, search, enabled)
    }

}