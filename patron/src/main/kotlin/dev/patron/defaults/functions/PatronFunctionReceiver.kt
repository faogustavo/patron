package dev.patron.defaults.functions

import dev.patron.builders.function.FunctionBuilder
import dev.patron.builders.function.FunctionBuilderBlock
import dev.patron.interfaces.function.FunctionReceiver
import dev.patron.interfaces.function.ReceivableFunctionSpec

class PatronFunctionReceiver(private val receivableFunctionSpec: ReceivableFunctionSpec) :
    FunctionReceiver {
    override fun newFunction(name: String, block: FunctionBuilderBlock) {
        FunctionBuilder.withSpec(name)
            .apply(block)
            .build()
            .let(receivableFunctionSpec::addFunction)
    }
}
