package dev.patron.interfaces.property

import com.squareup.kotlinpoet.PropertySpec

interface ReceivablePropertySpec {
    fun addProperty(propertySpec: PropertySpec)
}
