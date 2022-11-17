package com.sphirye.lonjas.repository.criteria

import com.sphirye.lonjas.entity.Artist
import com.sphirye.lonjas.service.tool.CriteriaTool
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class ArtistCriteria {

    @PersistenceContext
    lateinit var entityManager: EntityManager
    fun findFilterPageable(page: Int, size: Int): Page<Artist> {
        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Artist::class.java)
        val artist = query.from(Artist::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()

        query.select(artist).where(cb.and(*predicates.toTypedArray()))

        return CriteriaTool.page(entityManager, query, page, size)
    }
}