package dev.patron.functions

import dev.patron.functions.testers.BaseReturnableFunctionTester

open class ReturnableFunctionBuilderTest : BaseReturnableFunctionTester<ReturnableFunctionBuilder>() {

    override val subject by lazy { object : ReturnableFunctionBuilder(spec) {} }
}
