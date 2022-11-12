package com.sphirye.lonjas.config.exception

import java.lang.RuntimeException

class BadCredentialsException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("BadCredentials")
}