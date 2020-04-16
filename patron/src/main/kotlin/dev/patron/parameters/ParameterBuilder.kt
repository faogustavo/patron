package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import kotlin.reflect.KClass

abstract class BaseParameterBuilder<PIB : BaseParameterItemBuilder<*>>(
    protected val spec: FunSpec.Builder
) {
    protected abstract fun <T : Any> buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: KClass<T>
    ): PIB

    infix fun <T : Any> String.withType(type: KClass<T>) {
        buildParameterItemBuilder(
            spec = spec,
            name = this,
            type = type
        ).build()
    }

    fun <T : Any> String.withType(type: KClass<T>, block: PIB.() -> Unit) {
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

    override fun <T : Any> buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: KClass<T>
    ): PIB = buildParameterItemBuilder(
        classSpec = classSpec,
        spec = spec,
        name = name,
        type = type
    )

    abstract fun <T : Any> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<T>
    ): PIB
}

class LocalFunctionParameterBuilder(
    spec: FunSpec.Builder
) : BaseParameterBuilder<LocalFunctionParameterItemBuilder<*>>(spec) {

    override fun <T : Any> buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: KClass<T>
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

    override fun <T : Any> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<T>
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

    override fun <T : Any> buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<T>
    ) = ConstructorParameterItemBuilder(
        classSpec = classSpec,
        spec = spec,
        name = name,
        type = type
    )
}