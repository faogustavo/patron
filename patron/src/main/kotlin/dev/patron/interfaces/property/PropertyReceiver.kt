package dev.patron.interfaces.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.property.PropertyBuilderBlock

interface PropertyReceiver {
    fun newProperty(name: String, type: ClassName, block: PropertyBuilderBlock)
}
