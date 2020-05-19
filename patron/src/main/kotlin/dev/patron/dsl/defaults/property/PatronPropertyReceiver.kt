package dev.patron.dsl.defaults.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.property.PropertyBuilder
import dev.patron.dsl.builders.property.PropertyBuilderBlock
import dev.patron.dsl.interfaces.property.PropertyReceiver
import dev.patron.dsl.interfaces.property.ReceivablePropertySpec
import dev.patron.dsl.specs.PatronPropertySpec

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
