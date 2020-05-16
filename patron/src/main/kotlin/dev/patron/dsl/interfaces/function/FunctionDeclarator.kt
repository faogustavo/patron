package dev.patron.dsl.interfaces.function

import dev.patron.dsl.Block
import dev.patron.dsl.builders.function.FunctionBuilderBlock
import dev.patron.dsl.defaults.functions.PatronFunctionReceiver

typealias FunctionDeclaratorBlock = Block<FunctionDeclarator.Block>

interface FunctionDeclarator {
    fun functions(block: FunctionDeclaratorBlock)

    class Block(spec: ReceivableFunctionSpec) : FunctionReceiver by PatronFunctionReceiver(spec) {
        operator fun String.invoke(block: FunctionBuilderBlock) {
            newFunction(
                name = this,
                block = block
            )
        }
    }
}
