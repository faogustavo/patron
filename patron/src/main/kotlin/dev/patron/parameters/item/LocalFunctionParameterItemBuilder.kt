package dev.patron.parameters.item

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass

class LocalFunctionParameterItemBuilder(
    spec: FunSpec.Builder,
    name: String,
    type: ClassName
) : BaseParameterItemBuilder(spec, name, type) {

    constructor(
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ) : this(spec, name, type.asClassName())

    var isVararg: Boolean = false
        set(value) {
            field = value
            modifiers.add(KModifier.VARARG)
        }

    fun invoke(block: LocalFunctionParameterItemBuilder.() -> Unit) {
        apply(block)
    }
}