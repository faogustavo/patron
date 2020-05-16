package dev.patron.dsl.interfaces.parameter

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.parameter.ParameterBuilderBlock

interface ParameterReceiver {
    fun newParameter(name: String, type: ClassName, block: ParameterBuilderBlock)
}
