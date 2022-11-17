package com.sphirye.lonjas.service

import com.sphirye.lonjas.config.exception.DuplicatedException
import com.sphirye.lonjas.config.exception.NotFoundException
import com.sphirye.lonjas.entity.Artist
import com.sphirye.lonjas.repository.ArtistRepository
import com.sphirye.lonjas.repository.criteria.ArtistCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArtistService {

    @Autowired lateinit var  artistRepository: ArtistRepository
    @Autowired lateinit var twitterUserService: TwitterUserService
    @Autowired lateinit var artistCriteria: ArtistCriteria

    @Transactional
    fun createFromTwitter(twitterId: String): Artist {
        if (existsByTwitterId(twitterId)) { throw DuplicatedException("This user has already been linked to another artist entity.") }
        val artist = Artist()
        artist.twitter = twitterUserService.register(twitterId)
        return artistRepository.save(artist)
    }


    fun findById(id: Long): Artist {
        if (!existsById(id)) { throw NotFoundException("Artist not found") }
        return artistRepository.getReferenceById(id)
    }

    fun existsById(id: Long): Boolean {
        return artistRepository.existsById(id)
    }

    fun findByTwitterId(twitterId: String): Artist {
        if (!existsByTwitterId(twitterId)) { throw NotFoundException("Artist not found") }
        return artistRepository.findByTwitter_Id(twitterId)
    }

    fun existsByTwitterId(twitterId: String): Boolean {
        return artistRepository.existsByTwitter_id(twitterId)
    }

    fun findFilterPageable(page: Int, size: Int): Page<Artist> {
        return artistCriteria.findFilterPageable(page, size)
    }

}