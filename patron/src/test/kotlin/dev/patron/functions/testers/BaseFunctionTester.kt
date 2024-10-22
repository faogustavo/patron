package dev.patron.functions.testers

import com.squareup.kotlinpoet.FunSpec
import dev.patron.functions.BaseFunctionBuilder
import dev.patron.modifiers.Visibility
import dev.patron.statement.StatementBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.mockkStatic
import io.mockk.verify
import java.io.File
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue

abstract class BaseFunctionTester<T : BaseFunctionBuilder> {

    protected val builtSpec: FunSpec = mockk()
    protected val spec: FunSpec.Builder = mockk(relaxed = true)
    protected abstract val subject: T

    @Before
    open fun setUp() {
        every { spec.build() } returns builtSpec
        mockkConstructor(StatementBuilder::class)
    }

    @Test
    fun setVisibility_addModifier() {
        val visibility = Visibility.PRIVATE

        subject.visibility = visibility

        verify(exactly = 1) { spec.addModifiers(visibility.modifier) }
    }

    @Test
    fun codeFrom_withCode_addToFunction() {
        val code = "println(\"Hello World!\")"

        subject.codeFrom(code)

        verify(exactly = 1) { spec.addCode(code) }
    }

    @Test
    fun codeFrom_withFile_addToFunction() {
        val code = "println(\"Hello World!\")"
        val file = mockFile(code)

        subject.codeFrom(file)

        verify(exactly = 1) { spec.addCode(code) }
    }

    @Test
    fun statements_createsNewStatementBuilder() {
        var hasCalled = false
        val block: StatementBuilder.() -> Unit = { hasCalled = true }

        subject.statements(block)

        expectThat(hasCalled).isTrue()
    }

    @Test
    fun build_callsBuildFromSpec() {
        subject.build()

        verify(exactly = 1) { spec.build() }
    }

    @Test
    fun build_returnsBuiltSpec() {
        val result = subject.build()

        expectThat(result)
            .isEqualTo(builtSpec)
    }

    private fun mockFile(content: String): File {
        mockkStatic("kotlin.io.FilesKt__FileReadWriteKt")
        val file = mockk<File>()
        every { file.readText(any()) } returns content
        return file
    }
}
