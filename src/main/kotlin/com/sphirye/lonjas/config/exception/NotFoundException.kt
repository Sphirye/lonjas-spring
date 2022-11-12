package com.sphirye.lonjas.config.exception

import java.lang.RuntimeException

class NotFoundException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("NotFound")
}