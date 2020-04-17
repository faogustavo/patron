package dev.patron.parameters.item

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import dev.patron.Builder

abstract class BaseParameterItemBuilder(
    protected val funSpec: FunSpec.Builder,
    protected val name: String,
    protected val type: ClassName
) : Builder<ParameterSpec>() {
    protected val modifiers: MutableList<KModifier> = mutableListOf()

    var isNullable: Boolean = false
    var initWith: String? = null

    override fun build() = ParameterSpec.builder(
        name = name,
        type = type.copy(nullable = isNullable),
        modifiers = modifiers
    ).apply {
        initWith?.let { this.defaultValue(it) }
    }.build()
}