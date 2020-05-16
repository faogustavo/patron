package dev.patron.dsl.defaults.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.property.PropertyBuilder
import dev.patron.dsl.builders.property.PropertyBuilderBlock
import dev.patron.dsl.interfaces.property.PropertyReceiver
import dev.patron.dsl.interfaces.property.ReceivablePropertySpec

class PatronPropertyReceiver(private val receivablePropertySpec: ReceivablePropertySpec) : PropertyReceiver {

    override fun newProperty(name: String, type: ClassName, block: PropertyBuilderBlock) {
        PropertyBuilder.withSpec(name, type)
            .apply(block)
            .build()
            .let(receivablePropertySpec::addProperty)
    }
}
