package dev.patron.dsl.defaults.constructors

import dev.patron.dsl.interfaces.constructor.ConstructableSpec
import dev.patron.dsl.interfaces.constructor.ConstructorDeclarator
import dev.patron.dsl.interfaces.constructor.ConstructorDeclaratorBlock

class PatronConstructorDeclarator(private val spec: ConstructableSpec) : ConstructorDeclarator {

    override fun constructors(block: ConstructorDeclaratorBlock) {
        ConstructorDeclarator.Block(spec).apply(block)
    }
}
