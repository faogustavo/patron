package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import dev.patron.parameters.LocalFunctionParameterBuilder

class LocalFunctionBuilder(
    name: String
) : ReturnableFunctionBuilder(FunSpec.builder(name)) {

    fun parameters(block: LocalFunctionParameterBuilder.() -> Unit) {
        LocalFunctionParameterBuilder(spec = spec).apply(block)
    }
}