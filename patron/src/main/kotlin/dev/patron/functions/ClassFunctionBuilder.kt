package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

abstract class ClassFunctionBuilder(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : ReturnableFunctionBuilder(spec)
