package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.ConstructorParameterBuilder

class ConstructorBuilder(
    classSpec: TypeSpec.Builder
) : ClassFunctionBuilder(classSpec, FunSpec.constructorBuilder()) {

    fun parameters(block: ConstructorParameterBuilder.() -> Unit) {
        ConstructorParameterBuilder(
            classSpec = classSpec,
            spec = spec
        ).apply(block)
    }
}