package dev.patron.dsl.defaults.functions

import dev.patron.dsl.builders.function.FunctionBuilder
import dev.patron.dsl.builders.function.FunctionBuilderBlock
import dev.patron.dsl.interfaces.function.FunctionReceiver
import dev.patron.dsl.interfaces.function.ReceivableFunctionSpec

class PatronFunctionReceiver(private val receivableFunctionSpec: ReceivableFunctionSpec) : FunctionReceiver {
    override fun newFunction(name: String, block: FunctionBuilderBlock) {
        FunctionBuilder.withSpec(name)
            .apply(block)
            .build()
            .let(receivableFunctionSpec::addFunction)
    }
}
