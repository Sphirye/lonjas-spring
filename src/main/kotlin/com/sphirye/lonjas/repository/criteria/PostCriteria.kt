package com.sphirye.lonjas.repository.criteria

import com.sphirye.lonjas.entity.Post
import com.sphirye.lonjas.entity.Post_
import com.sphirye.lonjas.service.tool.CriteriaTool
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class PostCriteria {

    @PersistenceContext lateinit var entityManager: EntityManager

    fun findFilterPageable(page: Int, size: Int, artistId: Long?): Page<Post> {

        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Post::class.java)
        val post = query.from(Post::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()

        artistId?.let {
            predicates.add(cb.equal(post.get(Post_.id), it))
        }

        query.select(post).where(cb.and(*predicates.toTypedArray()))

        return CriteriaTool.page(entityManager, query, page, size)
    }

}