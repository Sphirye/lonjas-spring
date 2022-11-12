package com.sphirye.lonjas.config.exception

import java.lang.RuntimeException

class DuplicatedException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("Duplicated")
}