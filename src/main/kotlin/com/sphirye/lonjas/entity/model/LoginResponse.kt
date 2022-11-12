package com.sphirye.lonjas.entity.model

import com.sphirye.lonjas.entity.Authority
import com.sphirye.lonjas.entity.User

data class LoginResponse(
    var token: String? = null,
    var user: User? = null,
    var authorities: Set<Authority>? = null
)