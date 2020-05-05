package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import dev.patron.functions.testers.BaseReturnableFunctionTester
import dev.patron.parameters.LocalFunctionParameterBuilder
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isTrue

class LocalFunctionBuilderTest : BaseReturnableFunctionTester<LocalFunctionBuilder>() {

    override val subject by lazy { LocalFunctionBuilder("testFun") }

    @Before
    override fun setUp() {
        super.setUp()

        mockkObject(FunSpec)

        every { FunSpec.builder(any()) } returns spec
    }

    @Test
    fun parameters_createsConstructorParameterNewBuilder() {
        var hasFunBeenCalled = false
        val block: LocalFunctionParameterBuilder.() -> Unit = { hasFunBeenCalled = true }

        subject.parameters(block)

        expectThat(hasFunBeenCalled)
            .isTrue()
    }
}
