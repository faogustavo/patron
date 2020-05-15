package dev.patron.dsl.interfaces.objects

import com.squareup.kotlinpoet.TypeSpec

interface ReceivableObject {
    fun addObject(typeSpec: TypeSpec)
}
