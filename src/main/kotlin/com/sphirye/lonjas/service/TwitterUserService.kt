package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.twitter.TwitterUser
import com.sphirye.lonjas.repository.TwitterUserRepository
import com.sphirye.lonjas.service.connector.twitter.TwitterUserConnector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TwitterUserService {

    @Autowired lateinit var twitterUserConnector: TwitterUserConnector
    @Autowired lateinit var twitterUserRepository: TwitterUserRepository

    fun register(id: String): TwitterUser {
        if (existsById(id)) { throw DuplicatedException("Twitter user already exists") }

        val data = twitterUserConnector.getTwitterUserById(id).data!!
        val user = TwitterUser()

        user.name = data.name
        user.username = data.username
        user.profileImageUrl = data.profileImageUrl!!.replace("_normal", "")
        user.id = data.id

        return twitterUserRepository.save(user)
    }

    fun sync(id: String): TwitterUser {

        val user = findById(id)
        val data = twitterUserConnector.getTwitterUserById(id).data!!

        user.name = data.name
        user.username = data.username
        user.profileImageUrl = data.profileImageUrl!!.replace("_normal", "")

        return twitterUserRepository.save(user)
    }

    fun findById(id: String): TwitterUser {
        if (!existsById(id)) { throw NotFoundException("Twitter user not found") }
        return twitterUserRepository.findById(id)
    }

    fun existsById(id: String): Boolean {
        return twitterUserRepository.existsById(id)
    }

}