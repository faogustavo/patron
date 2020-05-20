package dev.patron.interfaces.parameter

import com.squareup.kotlinpoet.ParameterSpec

interface ReceivableParameter {
    fun addParameter(parameterSpec: ParameterSpec)
}
