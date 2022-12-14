package com.sphirye.lonjas.repository.criteria

import com.sphirye.lonjas.entity.Tag
import com.sphirye.lonjas.entity.Tag_
import com.sphirye.lonjas.service.tool.CriteriaTool
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class TagCriteria {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    fun findPublicFilterPageable(page: Int, size: Int, search: String?): Page<Tag> {

        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Tag::class.java)
        val tag = query.from(Tag::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()

        if (!search.isNullOrEmpty()) {
            predicates.add(cb.equal(tag.get(Tag_.name), search))
        }

        predicates.add(cb.equal(tag.get(Tag_.enabled), true))

        query.select(tag).where(cb.and(*predicates.toTypedArray()))
        return CriteriaTool.page(entityManager, query, page, size)
    }

    fun findFilterPageable(page: Int, size: Int, search: String?, enabled: Boolean?): Page<Tag> {

        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Tag::class.java)
        val tag = query.from(Tag::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()

        if (!search.isNullOrEmpty()) {
            predicates.add(cb.equal(tag.get(Tag_.name), search))
            predicates.add(cb.equal(tag.get(Tag_.id), search))
        }

        enabled?.let {
            predicates.add(cb.equal(tag.get(Tag_.enabled), it))
        }

        query.select(tag).where(cb.and(*predicates.toTypedArray()))
        return CriteriaTool.page(entityManager, query, page, size)
    }



}