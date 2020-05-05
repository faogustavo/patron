package dev.patron.enum

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.modifiers.Visibility
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isTrue

class EnumBuilderTest {

    private val spec: TypeSpec.Builder = mockk()
    private val builtSpec: TypeSpec = mockk()
    private val subject by lazy { EnumBuilder("EnumBuilderTest") }

    @Before
    fun setUp() {
        mockkObject(TypeSpec)

        every { TypeSpec.enumBuilder(any<String>()) } returns spec
        every { spec.addModifiers(any<KModifier>()) } returns spec
        every { spec.build() } returns builtSpec
    }

    @Test
    fun setVisibility_addModifier(): Unit = with(subject) {
        val expectedVisibility = Visibility.PRIVATE

        visibility = expectedVisibility

        verify(exactly = 1) { spec.addModifiers(expectedVisibility.modifier) }
    }

    @Test
    fun values_runsBuilderLambda(): Unit = with(subject) {
        var alreadyRunLambda = false

        values {
            alreadyRunLambda = true
        }

        expectThat(alreadyRunLambda)
            .isTrue()
    }
}
