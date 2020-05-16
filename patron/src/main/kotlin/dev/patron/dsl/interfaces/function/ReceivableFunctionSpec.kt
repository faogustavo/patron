package dev.patron.dsl.interfaces.function

import com.squareup.kotlinpoet.FunSpec

interface ReceivableFunctionSpec {
    fun addFunction(funSpec: FunSpec)
}
