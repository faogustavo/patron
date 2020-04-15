package dev.patron

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

abstract class BaseFunctionBuilder(
    protected val spec: FunSpec.Builder
) : Builder<FunSpec>() {

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    override fun build() = spec.build()
}

abstract class ClassFunctionBuilder(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseFunctionBuilder(spec)

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