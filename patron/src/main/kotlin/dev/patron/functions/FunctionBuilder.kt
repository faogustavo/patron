package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.FunctionParameterBuilder

class FunctionBuilder(
    classSpec: TypeSpec.Builder,
    name: String
) : ClassFunctionBuilder(classSpec, FunSpec.builder(name)) {

    fun parameters(block: FunctionParameterBuilder.() -> Unit) {
        FunctionParameterBuilder(
            classSpec = classSpec,
            spec = spec
        ).apply(block)
    }
}