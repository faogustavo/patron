package dev.patron.dsl.interfaces.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.Block
import dev.patron.dsl.builders.property.PropertyBuilderBlock
import dev.patron.dsl.defaults.property.PatronPropertyReceiver
import dev.patron.dsl.specs.PatronPropertySpec

typealias PropertyDeclaratorBlock = Block<PropertyDeclarator.Block>

interface PropertyDeclarator {
    fun properties(block: PropertyDeclaratorBlock)

    class Block(
        spec: ReceivablePropertySpec,
        scope: PatronPropertySpec.Scope
    ) : PropertyReceiver by PatronPropertyReceiver(spec, scope) {

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
