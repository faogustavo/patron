package dev.patron.dsl.defaults.parameter

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.parameter.ParameterBuilder
import dev.patron.dsl.builders.parameter.ParameterBuilderBlock
import dev.patron.dsl.interfaces.parameter.ParameterReceiver
import dev.patron.dsl.interfaces.parameter.ReceivableParameter

class PatronParameterReceiver(private val receivableParameter: ReceivableParameter) : ParameterReceiver {

    override fun newParameter(name: String, type: ClassName, block: ParameterBuilderBlock) {
        ParameterBuilder.withSpec(name, type)
            .apply(block)
            .build()
            .let(receivableParameter::addParameter)
    }
}
