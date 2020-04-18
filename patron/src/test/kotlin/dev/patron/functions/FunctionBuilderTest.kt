package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.FunctionParameterBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isTrue

internal class FunctionBuilderTest : ReturnableFunctionBuilderTest() {

    private val classSpec: TypeSpec.Builder = mockk()
    private val functionSubject by lazy { FunctionBuilder(classSpec, "testFun") }
    override val subject
        get() = functionSubject

    @Before
    override fun setUp() {
        super.setUp()

        mockkObject(FunSpec)
        mockkObject(FunSpec)

        every { FunSpec.builder(any()) } returns spec
    }

    @Test
    fun parameters_createsConstructorParameterNewBuilder() {
        var hasFunBeenCalled = false
        val block: FunctionParameterBuilder.() -> Unit = { hasFunBeenCalled = true }

        functionSubject.parameters(block)

        expectThat(hasFunBeenCalled)
            .isTrue()
    }
}
