package dev.patron.dsl.interfaces.constructor

import dev.patron.dsl.builders.function.FunctionBuilderBlock

interface ConstructorReceiver {
    fun newConstructor(block: FunctionBuilderBlock)
    fun newPrimaryConstructor(block: FunctionBuilderBlock)
}
