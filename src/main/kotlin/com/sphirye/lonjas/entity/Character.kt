package com.sphirye.lonjas.entity

import java.io.Serializable
import javax.persistence.*

@Entity
class Character (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,

    @ManyToOne @JoinColumn(name = "category_name", referencedColumnName = "name")
    var category: Category? = null,

    @Enumerated(EnumType.STRING)
    var gender: Gender? = Gender.OTHER,
): Serializable {
    enum class Gender { MALE, FEMALE, OTHER }
}