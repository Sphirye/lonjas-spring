package com.sphirye.lonjas.controller

import com.sphirye.lonjas.entity.Artist
import com.sphirye.lonjas.entity.twitter.Tweet
import com.sphirye.lonjas.service.ArtistService
import com.sphirye.lonjas.service.tool.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ArtistController {

    @Autowired lateinit var artistService: ArtistService

    @GetMapping("/public/artist/{id}")
    fun getArtist(@PathVariable id: Long): ResponseEntity<Artist> {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.findById(id))
    }

    @GetMapping("/public/artist")
    fun findArtists(@RequestParam page: Int, @RequestParam size: Int): ResponseEntity<List<Artist>> {
        val result = artistService.findFilterPageable(page, size)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }

    // Twitter

    @PostMapping("/api/artist/create/twitter")
    fun createFromTwitter(@RequestParam twitterId: String): ResponseEntity<Artist> {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.createFromTwitter(twitterId))
    }

    @GetMapping("/api/artist/{id}/tweets")
    fun findArtistTweets(
        @PathVariable id: Long,
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) search: String?
    ): ResponseEntity<List<Tweet>> {
        val result = artistService.findArtistTweets(id, page, size, search)
        return ResponseEntity.status(HttpStatus.OK)
            .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
            .body(result.content)
    }


}