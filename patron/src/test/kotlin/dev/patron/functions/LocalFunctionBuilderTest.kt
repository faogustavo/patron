package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import dev.patron.parameters.LocalFunctionParameterBuilder
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isTrue

class LocalFunctionBuilderTest : ReturnableFunctionBuilderTest() {

    private val localFunSubject by lazy { LocalFunctionBuilder("testFun") }
    override val subject
        get() = localFunSubject

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
        val block: LocalFunctionParameterBuilder.() -> Unit = { hasFunBeenCalled = true }

        localFunSubject.parameters(block)

        expectThat(hasFunBeenCalled)
            .isTrue()
    }

}