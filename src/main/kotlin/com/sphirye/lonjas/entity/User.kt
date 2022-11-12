package com.sphirye.lonjas.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "`USER`")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var email: String? = null,
    var username: String? = null,

    @JsonIgnore
    var password: String? = null,
    @Column(name = "activated")
    var isActivated: Boolean = false,
    @ManyToMany @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role", referencedColumnName = "role")]
    )
    var authorities: Set<Authority>? = null
)