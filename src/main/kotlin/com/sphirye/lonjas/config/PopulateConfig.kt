package com.sphirye.lonjas.config

import com.sphirye.lonjas.service.AuthorityService
import com.sphirye.lonjas.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class PopulateConfig(
//    private val artistService: ArtistService,
//    private val postService: PostService,
//    private val categoryService: CategoryService,
//    private val characterService: CharacterService,
//    private val authorityService: AuthorityService,
//    private val userService: UserService,
//    private val tagService: TagService,
) {

    @Autowired lateinit var authorityService: AuthorityService
    @Autowired lateinit var userService: UserService

    //Mocked data, remove before mounting to production
    @PostConstruct
    fun initDatabase() {
        authorityService.init()
        userService.init()
    }
}