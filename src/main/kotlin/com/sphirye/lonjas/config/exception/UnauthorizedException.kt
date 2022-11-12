package com.sphirye.lonjas.config.exception

import java.lang.RuntimeException

class UnauthorizedException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("Unauthorized")
}