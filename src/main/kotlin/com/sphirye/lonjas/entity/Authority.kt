package com.sphirye.lonjas.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "authority")
data class Authority (
    @Id @Column(name = "role")
    var role: String? = null
)