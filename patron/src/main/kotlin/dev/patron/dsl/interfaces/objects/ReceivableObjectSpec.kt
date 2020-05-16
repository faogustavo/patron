package dev.patron.dsl.interfaces.objects

import com.squareup.kotlinpoet.TypeSpec

interface ReceivableObjectSpec {
    fun addObject(typeSpec: TypeSpec)
}
