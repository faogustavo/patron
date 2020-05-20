package dev.patron.defaults.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.property.PropertyBuilder
import dev.patron.builders.property.PropertyBuilderBlock
import dev.patron.interfaces.property.PropertyReceiver
import dev.patron.interfaces.property.ReceivablePropertySpec
import dev.patron.specs.PatronPropertySpec

class PatronPropertyReceiver(
    private val receivablePropertySpec: ReceivablePropertySpec,
    private val scope: PatronPropertySpec.Scope
) : PropertyReceiver {

    override fun newProperty(name: String, type: ClassName, block: PropertyBuilderBlock) {
        PropertyBuilder.withSpec(name, type, scope)
            .apply(block)
            .build()
            .let(receivablePropertySpec::addProperty)
    }
}
