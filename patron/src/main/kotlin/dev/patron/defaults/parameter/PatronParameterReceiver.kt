package dev.patron.defaults.parameter

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.parameter.ParameterBuilder
import dev.patron.builders.parameter.ParameterBuilderBlock
import dev.patron.interfaces.parameter.ParameterReceiver
import dev.patron.interfaces.parameter.ReceivableParameter

class PatronParameterReceiver(private val receivableParameter: ReceivableParameter) :
    ParameterReceiver {

    override fun newParameter(name: String, type: ClassName, block: ParameterBuilderBlock) {
        ParameterBuilder.withSpec(name, type)
            .apply(block)
            .build()
            .let(receivableParameter::addParameter)
    }
}
