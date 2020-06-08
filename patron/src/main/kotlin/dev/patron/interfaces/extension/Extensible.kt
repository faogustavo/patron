package dev.patron.interfaces.extension

import com.squareup.kotlinpoet.ClassName

interface Extensible {
    fun extend(className: ClassName)
}
