package dev.patron.defaults.constructors

import dev.patron.interfaces.constructor.ConstructableSpec
import dev.patron.interfaces.constructor.ConstructorDeclarator
import dev.patron.interfaces.constructor.ConstructorDeclaratorBlock

class PatronConstructorDeclarator(private val spec: ConstructableSpec) :
    ConstructorDeclarator {

    override fun constructors(block: ConstructorDeclaratorBlock) {
        ConstructorDeclarator.Block(spec).apply(block)
    }
}
