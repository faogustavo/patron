package dev.patron.dsl.interfaces.property

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.Block
import dev.patron.dsl.builders.property.PropertyBuilderBlock
import dev.patron.dsl.defaults.property.PatronPropertyReceiver

typealias PropertyDeclaratorBlock = Block<PropertyDeclarator.Block>

interface PropertyDeclarator {
    fun properties(block: PropertyDeclaratorBlock)

    class Block(spec: ReceivablePropertySpec) : PropertyReceiver by PatronPropertyReceiver(spec) {

        operator fun Pair<String, ClassName>.invoke(block: PropertyBuilderBlock) {
            newProperty(
                name = this.first,
                type = this.second,
                block = block
            )
        }
    }
}
