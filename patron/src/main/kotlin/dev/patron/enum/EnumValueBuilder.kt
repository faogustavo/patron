package dev.patron.enum

import com.squareup.kotlinpoet.TypeSpec

class EnumValueBuilder(private val spec: TypeSpec.Builder) {

    operator fun String.unaryPlus() {
        add(this)
    }

    fun add(name: String) {
        spec.addEnumConstant(name)
    }
}
