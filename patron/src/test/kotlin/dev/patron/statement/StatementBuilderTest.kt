package dev.patron.statement

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import dev.patron.ext.getMember
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isTrue

open class StatementBuilderTest {

    protected val spec: FunSpec.Builder = mockk()
    private val subject by lazy { StatementBuilder(spec) }

    @Before
    fun setUp() {
        every { spec.addStatement(any()) } returns spec
        every { spec.addStatement(any(), *anyVararg()) } returns spec
        every { spec.beginControlFlow(any(), *anyVararg()) } returns spec
        every { spec.endControlFlow() } returns spec
    }

    @Test
    fun plusString_addToSpec(): Unit = with(subject) {
        val code = "println(\"Hello World!\")"

        +code

        verify(exactly = 1) { spec.addStatement(code) }
    }

    @Test
    fun add_withStringStatement_addToSpec(): Unit = with(subject) {
        val code = "println(\"Hello World!\")"

        add(code)

        verify(exactly = 1) { spec.addStatement(code) }
    }

    @Test
    fun add_withStringParameters_addToSpec(): Unit = with(subject) {
        val code = "println(\"Hello World!\")"
        val args: Array<Any> = listOf("lorem", "ipsum", "dolor", "sit").toTypedArray()

        add(code, *args)

        verify(exactly = 1) { spec.addStatement(code, *args) }
    }

    @Test
    fun add_withMixedParameters_addToSpec(): Unit = with(subject) {
        val code = "println(\"Hello World!\")"
        val args = listOf(
            "lorem",
            String::class,
            ClassName("", "Hello World"),
            ClassName("", "Hello World").getMember("testFun")
        ).toTypedArray()

        add(code, *args)

        verify(exactly = 1) { subject.add(code, *args) }
    }

    @Test
    fun controlFlow_startAndEndBlock(): Unit = with(subject) {
        val initCode = "if (1 == 1)"
        controlFlow(initCode) {}

        verify(exactly = 1) {
            spec.beginControlFlow(initCode)
            spec.endControlFlow()
        }
    }

    @Test
    fun controlFlow_callsBlock(): Unit = with(subject) {
        var hasBlockBeingCalled = false
        controlFlow("initCode") { hasBlockBeingCalled = true }

        expectThat(hasBlockBeingCalled)
            .isTrue()
    }

    @Test
    fun unitReturn_addReturnStatement(): Unit = with(subject) {
        unitReturn()

        verify { spec.addStatement("return") }
    }

    @Test
    fun unitReturn_onSpecificBlock_addReturnStatement(): Unit = with(subject) {
        unitReturn("test")

        verify { spec.addStatement("return@test") }
    }

    @Test
    fun returnWith_addReturnStatement(): Unit = with(subject) {
        returnWith("value")

        verify { spec.addStatement("return value") }
    }

    @Test

    fun returnWith_onSpecificBlock_addReturnStatement(): Unit = with(subject) {
        returnWith("value", blockName = "test")

        verify { spec.addStatement("return@test value") }
    }

    @Test
    fun returnWithString_addReturnStatement(): Unit = with(subject) {
        returnWithString("value")

        verify { spec.addStatement("return %S", "value") }
    }

    @Test

    fun returnWithString_onSpecificBlock_addReturnStatement(): Unit = with(subject) {
        returnWithString("value", blockName = "test")

        verify { spec.addStatement("return@test %S", "value") }
    }

    @Test
    fun returnWithStringTemplate_addReturnStatement(): Unit = with(subject) {
        returnWithStringTemplate("value")

        verify { spec.addStatement("return %P", "value") }
    }

    @Test

    fun returnWithStringTemplate_onSpecificBlock_addReturnStatement(): Unit = with(subject) {
        returnWithStringTemplate("value", blockName = "test")

        verify { spec.addStatement("return@test %P", "value") }
    }
}
