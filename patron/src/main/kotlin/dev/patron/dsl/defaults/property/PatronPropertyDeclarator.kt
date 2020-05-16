package dev.patron.dsl.defaults.property

import dev.patron.dsl.interfaces.property.PropertyDeclarator
import dev.patron.dsl.interfaces.property.PropertyDeclaratorBlock
import dev.patron.dsl.interfaces.property.ReceivablePropertySpec

class PatronPropertyDeclarator(private val spec: ReceivablePropertySpec) : PropertyDeclarator {

    override fun properties(block: PropertyDeclaratorBlock) {
        PropertyDeclarator.Block(spec).apply(block)
    }
}
