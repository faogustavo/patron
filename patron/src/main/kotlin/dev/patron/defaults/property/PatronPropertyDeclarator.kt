package dev.patron.defaults.property

import dev.patron.interfaces.property.PropertyDeclarator
import dev.patron.interfaces.property.PropertyDeclaratorBlock
import dev.patron.interfaces.property.ReceivablePropertySpec
import dev.patron.specs.PatronPropertySpec

class PatronPropertyDeclarator(
    private val spec: ReceivablePropertySpec,
    private val scope: PatronPropertySpec.Scope
) : PropertyDeclarator {

    override fun properties(block: PropertyDeclaratorBlock) {
        PropertyDeclarator.Block(spec, scope).apply(block)
    }
}
