package dev.patron.dsl.interfaces.property

import com.squareup.kotlinpoet.PropertySpec

interface ReceivablePropertySpec {
    fun addProperty(propertySpec: PropertySpec)
}
