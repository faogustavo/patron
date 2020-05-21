package dev.patron.specs

import com.squareup.kotlinpoet.CodeBlock
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PatronCodeSpecTest {

    private val builder = mockk<CodeBlock.Builder>()

    @Before
    fun setUp() {
        mockkObject(CodeBlock)

        every { CodeBlock.builder() } returns builder
        every { builder.addStatement(any(), *anyVararg()) } returns builder
        every { builder.beginControlFlow(any(), *anyVararg()) } returns builder
        every { builder.endControlFlow() } returns builder
        every { builder.build() } returns mockk()
    }

    @Test
    fun add_withoutArgs_shouldAddStatement() {
        val expectedStatement = "println(args[0])"

        testSpec().add(expectedStatement)

        verify(exactly = 1) { builder.addStatement(expectedStatement) }
    }

    @Test
    fun add_withArgs_shouldAddStatement() {
        val expectedStatement = "println(%S)"
        val expectedArguments: Array<Any> = arrayOf("Hello World!")

        testSpec().add(expectedStatement, *expectedArguments)

        verify(exactly = 1) { builder.addStatement(expectedStatement, *expectedArguments) }
    }

    @Test
    fun beginBlock_withoutArgs_shouldCallBeginControlFlow() {
        val expectedStatement = "if(4 > 5)"

        testSpec().beginBlock(expectedStatement)

        verify(exactly = 1) { builder.beginControlFlow(expectedStatement) }
    }

    @Test
    fun beginBlock_withArgs_shouldCallBeginControlFlow() {
        val expectedStatement = "if (%L > %L)"
        val expectedArguments: Array<Any> = arrayOf(4, 5)

        testSpec().beginBlock(expectedStatement, *expectedArguments)

        verify(exactly = 1) { builder.beginControlFlow(expectedStatement, *expectedArguments) }
    }

    @Test
    fun endBlock_shouldCallEndControlFlow() {
        testSpec().endBlock()

        verify(exactly = 1) { builder.endControlFlow() }
    }

    @Test
    fun build_shouldCallBuilderMethod() {
        testSpec().build()

        verify(exactly = 1) { builder.build() }
    }

    fun testSpec() = PatronCodeSpec()
}
