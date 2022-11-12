package com.sphirye.lonjas.config

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

    //Mocked data, remove before mounting to production
    @PostConstruct
    fun initDatabase() {
//        authorityService.create("ADMIN")    // ROLES
//        authorityService.create("MOD")
//
//        userService.create("admin", "1234", "ADMIN")
//        userService.create("mod", "1234", "MOD")
//

        //Tags
//        tagService.create("bbw")
//        tagService.create("ssbbw")
//        tagService.create("immobile")
//
//
//        categoryService.create("shantae")   // CATEGORIES
//        categoryService.create("mighty_switch_force")
//        categoryService.create("super_mario")
//        categoryService.create("vocaloid")
//
//
//        characterService.create("shantae", Character.Gender.FEMALE, "shantae")  // CHARACTERS
//        characterService.create("patricia_wagon", Character.Gender.FEMALE, "mighty_switch_force")
//        characterService.create("princess_peach", Character.Gender.FEMALE, "super_mario")
//        characterService.create("hatsune_miku", Character.Gender.FEMALE, "vocaloid")
//
//        artistService.create("fapolantern") // ARTISTS
//        artistService.create("sphirye") // ARTISTS
//
//        var bruh = mutableListOf<String>()  // POSTS
//        bruh.add("shantae")
//        postService.create("1559960200903360514", "shantae", listOf("shantae"), true, listOf("ssbbw"))
//        postService.create("1583012946547904512", "mighty_switch_force", listOf("patricia_wagon"), true, listOf("bbw"))
//        postService.create("1562904819794608129", "super_mario", listOf("princess_peach"), true, listOf("ssbbw"))
//        postService.create("1545961121500303360", "vocaloid", listOf("hatsune_miku"), true, listOf("immobile"))


    }
}