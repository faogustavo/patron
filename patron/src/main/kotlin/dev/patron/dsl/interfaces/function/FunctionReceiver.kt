package dev.patron.dsl.interfaces.function

import dev.patron.dsl.specs.function.FunctionBuilderBlock

interface FunctionReceiver {
    fun newFunction(name: String, block: FunctionBuilderBlock)
}
