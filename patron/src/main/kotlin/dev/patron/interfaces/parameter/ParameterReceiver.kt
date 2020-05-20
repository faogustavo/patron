package dev.patron.interfaces.parameter

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.parameter.ParameterBuilderBlock

interface ParameterReceiver {
    fun newParameter(name: String, type: ClassName, block: ParameterBuilderBlock)
}
