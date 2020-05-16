package dev.patron.dsl.interfaces.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.property.PropertyBuilderBlock

interface PropertyReceiver {
    fun newProperty(name: String, type: ClassName, block: PropertyBuilderBlock)
}
