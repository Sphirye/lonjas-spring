package com.sphirye.lonjas.repository.criteria

import com.sphirye.lonjas.entity.Category_
import com.sphirye.lonjas.entity.Character
import com.sphirye.lonjas.entity.Character_
import com.sphirye.lonjas.service.tool.CriteriaTool
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class CharacterCriteria {
    @PersistenceContext lateinit var entityManager: EntityManager

    fun findFilterPageable(page: Int, size: Int, search: String?): Page<Character> {
        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Character::class.java)
        val character = query.from(Character::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()

        if (!search.isNullOrEmpty()) {
            val word = search.trim().lowercase()
            val like = "%$word%"
            predicates.add(
                cb.or(
                    cb.like(cb.lower(character.get(Character_.name)), like),
                    cb.like(cb.lower(character.get(Character_.category).get(Category_.name)), like)
                )
            )
        }

        query.select(character).where(cb.and(*predicates.toTypedArray()))

        return CriteriaTool.page(entityManager, query, page, size)
    }
}