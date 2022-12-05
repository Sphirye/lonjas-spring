package com.sphirye.lonjas.entity

import javax.persistence.*

@Entity
class Category (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
//    @OneToMany(mappedBy = "category") @JsonIgnore
//    var characters: MutableSet<Character> = mutableSetOf()
)