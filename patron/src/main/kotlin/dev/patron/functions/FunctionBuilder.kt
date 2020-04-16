package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.Builder
import dev.patron.modifiers.Visibility
import dev.patron.parameters.ConstructorParameterBuilder
import dev.patron.parameters.FunctionParameterBuilder
import dev.patron.parameters.LocalFunctionParameterBuilder
import dev.patron.statement.StatementBuilder
import java.io.File
import kotlin.reflect.KClass

abstract class BaseFunctionBuilder(
    protected val spec: FunSpec.Builder
) : Builder<FunSpec>() {

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    fun statements(block: StatementBuilder.() -> Unit) {
        StatementBuilder(spec = spec).apply(block)
    }

    fun codeFrom(code: String) {
        spec.addCode(code)
    }

    fun codeFrom(file: File) {
        spec.addCode(file.readText())
    }

    override fun build() = spec.build()
}

abstract class ReturnableFunctionBuilder(
    spec: FunSpec.Builder
) : BaseFunctionBuilder(spec) {

    fun <T : Any> returning(type: KClass<T>) {
        spec.returns(type)
    }
}

abstract class ClassFunctionBuilder(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : ReturnableFunctionBuilder(spec)

class LocalFunctionBuilder(
    name: String
) : ReturnableFunctionBuilder(FunSpec.builder(name)) {

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

class GetterBuilder(private val type: TypeName) : BaseFunctionBuilder(FunSpec.getterBuilder()) {

    override fun build(): FunSpec {
        spec.returns(type)
        return super.build()
    }
}

class SetterBuilder(
    private val type: TypeName
) : BaseFunctionBuilder(FunSpec.setterBuilder()) {

    var name: String = "field"

    override fun build(): FunSpec {
        spec.addParameter(name, type)
        return super.build()
    }
}