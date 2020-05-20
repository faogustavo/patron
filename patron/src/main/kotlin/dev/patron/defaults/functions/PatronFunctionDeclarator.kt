package dev.patron.defaults.functions

import dev.patron.interfaces.function.FunctionDeclarator
import dev.patron.interfaces.function.FunctionDeclaratorBlock
import dev.patron.interfaces.function.ReceivableFunctionSpec

class PatronFunctionDeclarator(private val spec: ReceivableFunctionSpec) :
    FunctionDeclarator {

    override fun functions(block: FunctionDeclaratorBlock) {
        FunctionDeclarator.Block(spec).apply(block)
    }
}
