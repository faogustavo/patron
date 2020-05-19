package dev.patron.dsl.interfaces.constructor

import dev.patron.dsl.Block
import dev.patron.dsl.builders.function.FunctionBuilderBlock
import dev.patron.dsl.defaults.constructors.PatronConstructorReceiver

typealias ConstructorDeclaratorBlock = Block<ConstructorDeclarator.Block>

interface ConstructorDeclarator {

    fun constructors(block: ConstructorDeclaratorBlock)
    class Block(spec: ConstructableSpec) : ConstructorReceiver by PatronConstructorReceiver(spec) {
        fun primaryConstructor(block: FunctionBuilderBlock) {
            newPrimaryConstructor(block)
        }

        fun constructor(block: FunctionBuilderBlock) {
            newConstructor(block)
        }
    }
}
