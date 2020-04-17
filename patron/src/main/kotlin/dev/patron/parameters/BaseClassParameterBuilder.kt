package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.item.BaseParameterItemBuilder
import kotlin.reflect.KClass

abstract class BaseClassParameterBuilder<PIB : BaseParameterItemBuilder>(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseParameterBuilder<PIB>(spec) {

    override fun buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ): PIB = buildParameterItemBuilder(
        classSpec = classSpec,
        spec = spec,
        name = name,
        type = type
    )

    abstract fun buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ): PIB
}