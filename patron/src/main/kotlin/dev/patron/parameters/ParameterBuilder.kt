package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

abstract class BaseParameterBuilder<PIB : BaseParameterItemBuilder<*>>(
    protected val spec: FunSpec.Builder
) {
    protected abstract fun <T> buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): PIB

    infix fun <T> String.withType(type: Class<T>) {
        buildParameterItemBuilder(
            spec = spec,
            name = this,
            type = type
        ).build()
    }

    fun <T> String.withType(type: Class<T>, block: PIB.() -> Unit) {
        buildParameterItemBuilder(
            spec = spec,
            name = this,
            type = type
        ).apply(block).build()
    }
}

abstract class BaseClassParameterBuilder<PIB : BaseParameterItemBuilder<*>>(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseParameterBuilder<PIB>(spec) {

    override fun <T> buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): PIB = buildParameterItemBuilder(
        classSpec = classSpec,
        spec = spec,
        name = name,
        type = type
    )

    abstract fun <T> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): PIB
}

class LocalFunctionParameterBuilder(
    spec: FunSpec.Builder
) : BaseParameterBuilder<LocalFunctionParameterItemBuilder<*>>(spec) {

    override fun <T> buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): LocalFunctionParameterItemBuilder<T> =
        LocalFunctionParameterItemBuilder(
            spec = spec,
            name = name,
            type = type
        )
}

class FunctionParameterBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseClassParameterBuilder<FunctionParameterItemBuilder<*>>(classSpec, spec) {

    override fun <T> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): FunctionParameterItemBuilder<T> =
        FunctionParameterItemBuilder(
            classSpec = classSpec,
            spec = spec,
            name = name,
            type = type
        )
}

class ConstructorParameterBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseClassParameterBuilder<ConstructorParameterItemBuilder<*>>(classSpec, spec) {

    override fun <T> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: Class<T>
    ): ConstructorParameterItemBuilder<T> =
        ConstructorParameterItemBuilder(
            classSpec = classSpec,
            spec = spec,
            name = name,
            type = type
        )
}