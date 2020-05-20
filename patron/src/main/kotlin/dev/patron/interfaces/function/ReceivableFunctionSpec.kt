package dev.patron.interfaces.function

import com.squareup.kotlinpoet.FunSpec

interface ReceivableFunctionSpec {
    fun addFunction(funSpec: FunSpec)
}
