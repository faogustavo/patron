package dev.patron.dsl.interfaces.constructor

import com.squareup.kotlinpoet.FunSpec

interface ConstructableSpec {
    fun addConstructor(funSpec: FunSpec)
    fun addPrimaryConstructor(funSpec: FunSpec)
}
