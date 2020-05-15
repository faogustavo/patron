package dev.patron.dsl.defaults.functions

import dev.patron.dsl.builders.function.FunctionBuilder
import dev.patron.dsl.builders.function.FunctionBuilderBlock
import dev.patron.dsl.interfaces.function.FunctionReceiver
import dev.patron.dsl.interfaces.function.ReceivableFunction
import dev.patron.dsl.specs.PatronFunctionSpec

class PatronFunctionReceiver(private val receivableFunction: ReceivableFunction) : FunctionReceiver {
    override fun newFunction(name: String, block: FunctionBuilderBlock) {
        FunctionBuilder(PatronFunctionSpec.withSpec(name))
            .apply(block)
            .build()
            .let(receivableFunction::addFunction)
    }
}
