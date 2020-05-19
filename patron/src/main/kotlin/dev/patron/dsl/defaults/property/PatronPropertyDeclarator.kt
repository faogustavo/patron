package dev.patron.dsl.defaults.property

import dev.patron.dsl.interfaces.property.PropertyDeclarator
import dev.patron.dsl.interfaces.property.PropertyDeclaratorBlock
import dev.patron.dsl.interfaces.property.ReceivablePropertySpec
import dev.patron.dsl.specs.PatronPropertySpec

class PatronPropertyDeclarator(
    private val spec: ReceivablePropertySpec,
    private val scope: PatronPropertySpec.Scope
) : PropertyDeclarator {

    override fun properties(block: PropertyDeclaratorBlock) {
        PropertyDeclarator.Block(spec, scope).apply(block)
    }
}
