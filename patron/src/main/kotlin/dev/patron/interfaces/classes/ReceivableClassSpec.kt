package dev.patron.interfaces.classes

import com.squareup.kotlinpoet.TypeSpec

interface ReceivableClassSpec {
    fun addClass(typeSpec: TypeSpec)
}
