package dev.patron.dsl.interfaces.enums

import com.squareup.kotlinpoet.TypeSpec

interface ReceivableEnum {
    fun addEnum(typeSpec: TypeSpec)
}
