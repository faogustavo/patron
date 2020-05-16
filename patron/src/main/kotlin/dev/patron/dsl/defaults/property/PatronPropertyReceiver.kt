package dev.patron.dsl.defaults.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.property.PropertyBuilder
import dev.patron.dsl.builders.property.PropertyBuilderBlock
import dev.patron.dsl.interfaces.property.PropertyReceiver
import dev.patron.dsl.interfaces.property.ReceivableProperty

class PatronPropertyReceiver(private val receivableProperty: ReceivableProperty) : PropertyReceiver {

    override fun newProperty(name: String, type: ClassName, block: PropertyBuilderBlock) {
        PropertyBuilder.withSpec(name, type)
            .apply(block)
            .build()
            .let(receivableProperty::addProperty)
    }
}
