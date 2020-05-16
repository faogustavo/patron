package dev.patron.dsl.interfaces.enums

import com.squareup.kotlinpoet.TypeSpec

interface ReceivableEnumSpec {
    fun addEnum(typeSpec: TypeSpec)
}
