package dev.patron.dsl.interfaces.property

import com.squareup.kotlinpoet.PropertySpec

interface ReceivableProperty {
    fun addProperty(propertySpec: PropertySpec)
}
