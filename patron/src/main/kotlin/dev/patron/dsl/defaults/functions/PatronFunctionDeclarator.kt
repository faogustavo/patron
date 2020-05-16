package dev.patron.dsl.defaults.functions

import dev.patron.dsl.interfaces.function.FunctionDeclarator
import dev.patron.dsl.interfaces.function.FunctionDeclaratorBlock
import dev.patron.dsl.interfaces.function.ReceivableFunctionSpec

class PatronFunctionDeclarator(private val spec: ReceivableFunctionSpec) : FunctionDeclarator {

    override fun functions(block: FunctionDeclaratorBlock) {
        FunctionDeclarator.Block(spec).apply(block)
    }
}
