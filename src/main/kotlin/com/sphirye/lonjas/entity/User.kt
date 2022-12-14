package com.sphirye.lonjas.entity

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(
    name = "`USER`",
    uniqueConstraints = [UniqueConstraint(columnNames = [User_.EMAIL])]
)
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var email: String? = null,
    var username: String? = null,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String? = null,
    var enabled: Boolean = false,
    @ManyToMany @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role", referencedColumnName = "role")]
    )
    var authorities: Set<Authority>? = null
)