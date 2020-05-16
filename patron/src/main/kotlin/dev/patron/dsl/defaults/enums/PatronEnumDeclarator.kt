package dev.patron.dsl.defaults.enums

import dev.patron.dsl.interfaces.enums.EnumDeclarator
import dev.patron.dsl.interfaces.enums.EnumDeclaratorBlock
import dev.patron.dsl.interfaces.enums.ReceivableEnumSpec

class PatronEnumDeclarator(private val spec: ReceivableEnumSpec) : EnumDeclarator {

    override fun enums(block: EnumDeclaratorBlock) {
        EnumDeclarator.Block(spec).apply(block)
    }
}
