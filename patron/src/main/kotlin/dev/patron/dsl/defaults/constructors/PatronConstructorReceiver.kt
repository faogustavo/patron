package dev.patron.dsl.defaults.constructors

import dev.patron.dsl.builders.function.FunctionBuilder
import dev.patron.dsl.builders.function.FunctionBuilderBlock
import dev.patron.dsl.interfaces.constructor.ConstructableSpec
import dev.patron.dsl.interfaces.constructor.ConstructorReceiver

class PatronConstructorReceiver(private val spec: ConstructableSpec) : ConstructorReceiver {

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
