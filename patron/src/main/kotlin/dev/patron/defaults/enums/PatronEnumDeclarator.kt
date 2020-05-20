package dev.patron.defaults.enums

import dev.patron.interfaces.enums.EnumDeclarator
import dev.patron.interfaces.enums.EnumDeclaratorBlock
import dev.patron.interfaces.enums.ReceivableEnumSpec

class PatronEnumDeclarator(private val spec: ReceivableEnumSpec) :
    EnumDeclarator {

    override fun enums(block: EnumDeclaratorBlock) {
        EnumDeclarator.Block(spec).apply(block)
    }
}
