package dev.patron.interfaces.function

import dev.patron.Block
import dev.patron.builders.function.FunctionBuilderBlock
import dev.patron.defaults.functions.PatronFunctionReceiver

typealias FunctionDeclaratorBlock = Block<FunctionDeclarator.Block>

interface FunctionDeclarator {
    fun functions(block: FunctionDeclaratorBlock)

    class Block(spec: ReceivableFunctionSpec) : FunctionReceiver by PatronFunctionReceiver(
        spec
    ) {
        operator fun String.invoke(block: FunctionBuilderBlock) {
            newFunction(
                name = this,
                block = block
            )
        }
    }
}
