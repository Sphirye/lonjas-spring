package com.sphirye.lonjas.config.exception

import java.lang.RuntimeException

class ConflictException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("Conflict")
}