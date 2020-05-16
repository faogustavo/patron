package dev.patron.dsl.interfaces.classes

import com.squareup.kotlinpoet.TypeSpec

interface ReceivableClassSpec {
    fun addClass(typeSpec: TypeSpec)
}
