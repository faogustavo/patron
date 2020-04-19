package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import dev.patron.functions.testers.BaseFunctionTester

open class BaseFunctionBuilderTest : BaseFunctionTester<BaseFunctionBuilder>() {

    private class BaseFunctionBuilderImpl(spec: FunSpec.Builder) : BaseFunctionBuilder(spec)

    override val subject: BaseFunctionBuilder by lazy { BaseFunctionBuilderImpl(spec) }
}