package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.ConstructorParameterBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isTrue

internal class ConstructorBuilderTest : ReturnableFunctionBuilderTest() {
    private val classSpec: TypeSpec.Builder = mockk()
    private val constructorSubject by lazy { ConstructorBuilder(classSpec) }

    override val subject
        get() = constructorSubject

    @Before
    override fun setUp() {
        super.setUp()

        mockkObject(FunSpec)
        mockkObject(FunSpec)

        every { FunSpec.constructorBuilder() } returns spec
    }

    @Test
    fun parameters_createsConstructorParameterNewBuilder() {
        var hasFunBeenCalled = false
        val block: ConstructorParameterBuilder.() -> Unit = { hasFunBeenCalled = true }

        constructorSubject.parameters(block)

        expectThat(hasFunBeenCalled)
            .isTrue()
    }
}