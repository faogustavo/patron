package dev.patron.dsl.interfaces.classes

import com.squareup.kotlinpoet.TypeSpec

interface ReceivableClass {
    fun addClass(typeSpec: TypeSpec)
}
