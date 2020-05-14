package dev.patron.dsl.interfaces.function

import dev.patron.dsl.specs.function.FunctionBuilderBlock

interface FunctionReceiver {
    fun function(name: String, block: FunctionBuilderBlock)
}
