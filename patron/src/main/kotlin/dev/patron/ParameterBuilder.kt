package dev.patron

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

abstract class BaseParameterBuilder<PIB : BaseParameterItemBuilder<*>>(
    protected val classSpec: TypeSpec.Builder,
    protected val spec: FunSpec.Builder
) {

    protected abstract fun <T> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): PIB

    infix fun <T> String.withType(type: Class<T>): PIB {
        spec.addParameter(this, type)
        return buildParameterItemBuilder(
            classSpec, spec, this, type
        )
    }

    fun <T> String.withType(type: Class<T>, block: PIB.() -> Unit) {
        spec.addParameter(this, type)
        buildParameterItemBuilder(
            classSpec, spec, this, type
        ).apply(block)
    }
}

class FunctionParameterBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseParameterBuilder<FunctionParameterItemBuilder<*>>(classSpec, spec) {

    override fun <T> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): FunctionParameterItemBuilder<T> = FunctionParameterItemBuilder(
        classSpec = classSpec,
        spec = spec,
        name = name,
        type = type
    )
}

class ConstructorParameterBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseParameterBuilder<ConstructorParameterItemBuilder<*>>(classSpec, spec) {

    override fun <T> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): ConstructorParameterItemBuilder<T> = ConstructorParameterItemBuilder(
        classSpec = classSpec,
        spec = spec,
        name = name,
        type = type
    )
}