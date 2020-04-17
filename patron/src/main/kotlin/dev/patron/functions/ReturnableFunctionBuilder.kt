package dev.patron.functions

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import kotlin.reflect.KClass

abstract class ReturnableFunctionBuilder(
    spec: FunSpec.Builder
) : BaseFunctionBuilder(spec) {

    fun returning(type: KClass<*>) {
        spec.returns(type)
    }

    fun returning(type: ClassName) {
        spec.returns(type)
    }
}