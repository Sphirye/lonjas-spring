package com.sphirye.lonjas.repository.criteria

import com.sphirye.lonjas.entity.Artist_
import com.sphirye.lonjas.entity.Category_
import com.sphirye.lonjas.entity.Character_
import com.sphirye.lonjas.entity.Post
import com.sphirye.lonjas.entity.Post_
import com.sphirye.lonjas.entity.Tag_
import com.sphirye.lonjas.service.tool.CriteriaTool
import com.sphirye.lonjas.service.tool.RetrofitTool.gson
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class PostCriteria {

    @PersistenceContext lateinit var entityManager: EntityManager

    fun findFilterPageable(
        page: Int, size: Int, search: String?, artistId: Long?, categoryIds: List<Long>?,
        characterIds: List<Long>?, tagIds: List<Long>?, enabled: Boolean?
    ): Page<Post> {

        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Post::class.java)
        val post = query.from(Post::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()
        val categories = mutableListOf<Predicate>()
        val tags = mutableListOf<Predicate>()
        val characters = mutableListOf<Predicate>()

        artistId?.let { predicates.add(cb.equal(post.get(Post_.artist).get(Artist_.id), it)) }
        enabled?.let { predicates.add(cb.equal(post.get(Post_.enabled), enabled)) }

        categoryIds?.let {
            val inList = cb.`in`(post.join(Post_.categories).get(Category_.id))
            it.forEach { id -> inList.value(id) }
            categories.add(inList)
            predicates.add(cb.or(*categories.toTypedArray()))
        }

        tagIds?.let {
            val inList = cb.`in`(post.join(Post_.tags).get(Tag_.id))
            it.forEach { id -> inList.value(id) }
            tags.add(inList)
            predicates.add(cb.or(*tags.toTypedArray()))
        }

        characterIds?.let {
            val inList = cb.`in`(post.join(Post_.characters).get(Character_.id))
            it.forEach { id -> inList.value(id) }
            characters.add(inList)
            predicates.add(cb.or(*characters.toTypedArray()))
        }

        query.select(post).where(cb.and(*predicates.toTypedArray()))

        return CriteriaTool.page(entityManager, query, page, size)
    }

    fun findFilterPageableByArtis(page: Int, size: Int, artistId: Long?, public: Boolean? = true): Page<Post> {

        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Post::class.java)
        val post = query.from(Post::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()

        artistId?.let {
            predicates.add(cb.equal(post.get(Post_.artist).get(Artist_.id), it))
        }

        query.select(post).where(cb.and(*predicates.toTypedArray()))

        return CriteriaTool.page(entityManager, query, page, size)
    }

}