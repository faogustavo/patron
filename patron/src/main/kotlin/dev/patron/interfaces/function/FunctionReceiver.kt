package dev.patron.interfaces.function

import dev.patron.builders.function.FunctionBuilderBlock

interface FunctionReceiver {
    fun newFunction(name: String, block: FunctionBuilderBlock)
}
