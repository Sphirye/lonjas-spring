package com.sphirye.lonjas.repository.criteria

import com.sphirye.lonjas.entity.Category_
import com.sphirye.lonjas.entity.twitter.Tweet
import com.sphirye.lonjas.entity.twitter.Tweet_
import com.sphirye.lonjas.entity.twitter.TwitterUser_
import com.sphirye.lonjas.service.tool.CriteriaTool
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class TweetCriteria {

    @PersistenceContext lateinit var entityManager: EntityManager

    fun findFilterPageable(page: Int, size: Int, search: String?, twitterId: String): Page<Tweet> {
        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Tweet::class.java)
        val tweet = query.from(Tweet::class.java)
        val predicates: MutableList<Predicate> = mutableListOf()

        if (!search.isNullOrEmpty()) {
            val word = search.trim().lowercase()
            val like = "%$word%"
            predicates.add(
                cb.or(
                    cb.like(cb.lower(tweet.get(Tweet_.text)), like)
                )
            )
        }

        query.select(tweet).where(
            cb.and(*predicates.toTypedArray(),
            cb.equal(tweet.get(Tweet_.author).get(TwitterUser_.id), twitterId)
            )
        )

        return CriteriaTool.page(entityManager, query, page, size)

    }

}