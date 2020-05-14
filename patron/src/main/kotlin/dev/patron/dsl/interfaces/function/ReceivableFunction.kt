package dev.patron.dsl.interfaces.function

import com.squareup.kotlinpoet.FunSpec

interface ReceivableFunction {
    fun addFunction(funSpec: FunSpec)
}
