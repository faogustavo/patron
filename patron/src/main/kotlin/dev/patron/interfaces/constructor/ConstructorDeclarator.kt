package dev.patron.interfaces.constructor

import dev.patron.Block
import dev.patron.builders.function.FunctionBuilderBlock
import dev.patron.defaults.constructors.PatronConstructorReceiver

typealias ConstructorDeclaratorBlock = Block<ConstructorDeclarator.Block>

interface ConstructorDeclarator {

    fun constructors(block: ConstructorDeclaratorBlock)
    class Block(spec: ConstructableSpec) : ConstructorReceiver by PatronConstructorReceiver(
        spec
    ) {
        fun primaryConstructor(block: FunctionBuilderBlock) {
            newPrimaryConstructor(block)
        }

        fun constructor(block: FunctionBuilderBlock) {
            newConstructor(block)
        }
    }
}
