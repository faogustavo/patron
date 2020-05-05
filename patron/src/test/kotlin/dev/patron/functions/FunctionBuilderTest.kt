package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.functions.testers.BaseReturnableFunctionTester
import dev.patron.parameters.FunctionParameterBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isTrue

internal class FunctionBuilderTest : BaseReturnableFunctionTester<FunctionBuilder>() {

    private val classSpec: TypeSpec.Builder = mockk()
    override val subject by lazy { FunctionBuilder(classSpec, "testFun") }

    @Before
    override fun setUp() {
        super.setUp()

        mockkObject(FunSpec)

        every { FunSpec.builder(any()) } returns spec
    }

    @Test
    fun parameters_createsConstructorParameterNewBuilder() {
        var hasFunBeenCalled = false
        val block: FunctionParameterBuilder.() -> Unit = { hasFunBeenCalled = true }

        subject.parameters(block)

        expectThat(hasFunBeenCalled)
            .isTrue()
    }
}
