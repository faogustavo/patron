package dev.patron.parameters.item

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

abstract class BaseClassParameterItemBuilder(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: ClassName
) : BaseParameterItemBuilder(spec, name, type)
