package com.caperezh.baubaptest.common

import com.caperezh.baubaptest.R

data class BaseError(
    var cause: String = "Error en la conexión",
    var code: Int = -400,
    var exception: Exception? = null
)