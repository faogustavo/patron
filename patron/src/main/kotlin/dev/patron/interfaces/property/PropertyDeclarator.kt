package dev.patron.interfaces.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.Block
import dev.patron.builders.property.PropertyBuilderBlock
import dev.patron.defaults.property.PatronPropertyReceiver
import dev.patron.specs.PatronPropertySpec

typealias PropertyDeclaratorBlock = Block<PropertyDeclarator.Block>

interface PropertyDeclarator {
    fun properties(block: PropertyDeclaratorBlock)

    class Block(
        spec: ReceivablePropertySpec,
        scope: PatronPropertySpec.Scope
    ) : PropertyReceiver by PatronPropertyReceiver(
        spec,
        scope
    ) {

        operator fun Pair<String, ClassName>.unaryMinus() {
            newProperty(
                name = this.first,
                type = this.second,
                block = {}
            )
        }

        operator fun Pair<String, ClassName>.invoke(block: PropertyBuilderBlock) {
            newProperty(
                name = this.first,
                type = this.second,
                block = block
            )
        }
    }
}
