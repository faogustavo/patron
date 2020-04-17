package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import dev.patron.parameters.item.BaseParameterItemBuilder
import kotlin.reflect.KClass

abstract class BaseParameterBuilder<PIB : BaseParameterItemBuilder>(
    protected val spec: FunSpec.Builder
) {
    protected abstract fun buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ): PIB

    infix fun String.withType(type: KClass<*>) {
        buildParameterItemBuilder(
            spec = spec,
            name = this,
            type = type
        ).build().let(spec::addParameter)
    }

    fun String.withType(type: KClass<*>, block: PIB.() -> Unit) {
        buildParameterItemBuilder(
            spec = spec,
            name = this,
            type = type
        ).apply(block).build().let(spec::addParameter)
    }
}