package dev.patron.parameters.item

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass

abstract class BaseClassParameterItemBuilder(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: ClassName
) : BaseParameterItemBuilder(spec, name, type) {

    constructor(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ) : this(classSpec, spec, name, type.asClassName())
}
