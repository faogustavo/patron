package dev.patron.parameters.item

import com.squareup.kotlinpoet.*
import kotlin.reflect.KClass

class FunctionParameterItemBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: ClassName
) : BaseClassParameterItemBuilder(classSpec, spec, name, type) {

    constructor(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ) : this(classSpec, spec, name, type.asClassName())

    var isVararg: Boolean = false
        set(value) {
            field = value
            modifiers.add(KModifier.VARARG)
        }

    fun invoke(block: FunctionParameterItemBuilder.() -> Unit) {
        apply(block)
    }
}