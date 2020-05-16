package dev.patron.dsl.interfaces.parameter

import com.squareup.kotlinpoet.ParameterSpec

interface ReceivableParameter {
    fun addParameter(parameterSpec: ParameterSpec)
}
