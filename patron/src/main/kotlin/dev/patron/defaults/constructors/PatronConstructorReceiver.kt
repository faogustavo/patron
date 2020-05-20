package dev.patron.defaults.constructors

import dev.patron.builders.function.FunctionBuilder
import dev.patron.builders.function.FunctionBuilderBlock
import dev.patron.interfaces.constructor.ConstructableSpec
import dev.patron.interfaces.constructor.ConstructorReceiver

class PatronConstructorReceiver(private val spec: ConstructableSpec) :
    ConstructorReceiver {

    override fun newConstructor(block: FunctionBuilderBlock) {
        FunctionBuilder.withConstructorSpec()
            .apply(block)
            .build()
            .let(spec::addConstructor)
    }

    override fun newPrimaryConstructor(block: FunctionBuilderBlock) {
        FunctionBuilder.withConstructorSpec()
            .apply(block)
            .build()
            .let(spec::addPrimaryConstructor)
    }
}
