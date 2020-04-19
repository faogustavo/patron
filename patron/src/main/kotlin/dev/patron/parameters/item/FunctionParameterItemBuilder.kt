package dev.patron.parameters.item

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
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
            if (field) {
                modifiers.add(KModifier.VARARG)
            }
        }
}
