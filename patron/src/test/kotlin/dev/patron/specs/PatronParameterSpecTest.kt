package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import dev.patron.LITERAL_MARKER
import dev.patron.STRING_MARKER
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PatronParameterSpecTest {

    private val builder = mockk<ParameterSpec.Builder>()

    @Before
    fun setUp() {
        mockkObject(ParameterSpec)

        every {
            ParameterSpec.builder(any(), any<TypeName>(), *anyVararg())
        } returns builder

        every { builder.addAnnotation(any<AnnotationSpec>()) } returns builder
        every { builder.addModifiers(any<Iterable<KModifier>>()) } returns builder
        every { builder.addAnnotations(any()) } returns builder
        every { builder.defaultValue(any(), *anyVararg()) } returns builder
        every { builder.build() } returns mockk()
    }

    @Test
    fun build_shouldCallBuilderMethod() = with(testSpec()) {
        build()

        verify(exactly = 1) { builder.build() }
    }

    @Test
    fun isNullable_withFalse_setTypeAsNullable() = with(testSpec()) {
        isNullable = false
        build()

        verify(exactly = 1) {
            ParameterSpec.builder(
                any(),
                withArg<TypeName> { assertFalse(it.isNullable) },
                *anyVararg()
            )
        }
    }

    @Test
    fun isNullable_withTrue_setTypeAsNullable() = with(testSpec()) {
        isNullable = true
        build()

        verify(exactly = 1) {
            ParameterSpec.builder(
                any(),
                withArg<TypeName> { assertTrue(it.isNullable) },
                *anyVararg()
            )
        }
    }

    @Test
    fun isVarargs_withTrue_addModifier() = with(testSpec()) {
        isVarargs = true
        build()

        verify(exactly = 1) {
            builder.addModifiers(listOf(KModifier.VARARG))
        }
    }

    @Test
    fun isVarargs_withFalse_doesNotAddModifier() = with(testSpec()) {
        isVarargs = true
        isVarargs = false
        build()

        verify(exactly = 1) {
            builder.addModifiers(listOf())
        }
    }

    @Test
    fun initWith_withJustArgs_andStringType_initWithString() = with(testSpec()) {
        val expectedValue = "Lorem ipsum dolor"

        initWith(expectedValue)
        build()

        verify(exactly = 1) {
            builder.defaultValue(STRING_MARKER, expectedValue)
        }
    }

    @Test
    fun initWith_withJustArgs_andIntType_initLiteralValue() = with(testSpec(type = Int::class.asClassName())) {
        val expectedValue = 1

        initWith(expectedValue)
        build()

        verify(exactly = 1) {
            builder.defaultValue(LITERAL_MARKER, expectedValue)
        }
    }

    @Test
    fun initWithFormat_initWithFormatAndValue() = with(testSpec()) {
        val expectedFormat = "%S + %L + %L + %S"
        val expectedValue = arrayOf<Any>("foo", 3, 14, "bar")

        initWithFormat(expectedFormat, *expectedValue)
        build()

        verify(exactly = 1) {
            builder.defaultValue(expectedFormat, *expectedValue)
        }
    }

    @Test
    fun annotateWith_addAnnotation() = with(testSpec()) {
        val annotation = mockk<AnnotationSpec>()

        annotateWith(annotation)
        build()

        verify(exactly = 1) {
            builder.addAnnotations(listOf(annotation))
        }
    }

    private fun testSpec(
        name: String = "testParameter",
        type: ClassName = String::class.asClassName()
    ) = PatronParameterSpec(name, type)
}
