package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.Builder
import dev.patron.modifiers.Visibility
import dev.patron.parameters.ConstructorParameterBuilder
import dev.patron.parameters.FunctionParameterBuilder
import dev.patron.parameters.LocalFunctionParameterBuilder
import dev.patron.statement.StatementBuilder

abstract class BaseFunctionBuilder(
    protected val spec: FunSpec.Builder
) : Builder<FunSpec>() {

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    fun <T> returning(type: Class<T>) {
        spec.returns(type)
    }

    fun statements(block: StatementBuilder.() -> Unit) {
        StatementBuilder(spec = spec).apply(block)
    }

    override fun build() = spec.build()
}

abstract class ClassFunctionBuilder(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseFunctionBuilder(spec)

class LocalFunctionBuilder(
    name: String
) : BaseFunctionBuilder(FunSpec.builder(name)) {

    fun parameters(block: LocalFunctionParameterBuilder.() -> Unit) {
        LocalFunctionParameterBuilder(spec = spec).apply(block)
    }
}

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