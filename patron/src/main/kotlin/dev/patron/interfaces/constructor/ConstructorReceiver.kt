package dev.patron.interfaces.constructor

import dev.patron.builders.function.FunctionBuilderBlock

interface ConstructorReceiver {
    fun newConstructor(block: FunctionBuilderBlock)
    fun newPrimaryConstructor(block: FunctionBuilderBlock)
}
