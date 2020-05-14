package dev.patron.dsl.defaults.functions

import dev.patron.dsl.interfaces.function.FunctionReceiver
import dev.patron.dsl.interfaces.function.ReceivableFunction
import dev.patron.dsl.specs.function.FunctionBuilder
import dev.patron.dsl.specs.function.FunctionBuilderBlock
import dev.patron.dsl.specs.function.PatronFunctionSpec

class PatronFunctionReceiver(private val receivableFunction: ReceivableFunction) : FunctionReceiver {
    override fun function(name: String, block: FunctionBuilderBlock) {
        FunctionBuilder(PatronFunctionSpec.withSpec(name))
            .apply(block)
            .build()
            .let { receivableFunction.addFunction(it) }
    }
}
