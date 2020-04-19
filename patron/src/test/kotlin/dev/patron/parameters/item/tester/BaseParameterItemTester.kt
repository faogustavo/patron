package dev.patron.parameters.item.tester

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import dev.patron.parameters.item.BaseParameterItemBuilder
import io.mockk.*
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.kotlinpoet.isNullable

abstract class BaseParameterItemTester<T : BaseParameterItemBuilder> {

    protected val builtSpec: ParameterSpec = mockk()
    protected val spec: ParameterSpec.Builder = mockk()
    protected val funSpec: FunSpec.Builder = mockk()

    protected val typeSlot = slot<TypeName>()
    protected val modifiersSlot = slot<Iterable<KModifier>>()

    protected abstract val subject: T

    @Before
    open fun setUp() {
        mockkObject(ParameterSpec)
        mockkObject(ParameterSpec.Companion)

        every {
            ParameterSpec.builder(
                name = any(),
                type = capture(typeSlot),
                modifiers = capture(modifiersSlot)
            )
        } returns spec

        every { spec.defaultValue(any(), *anyVararg()) } returns spec
        every { spec.build() } returns builtSpec
    }

    @Test
    fun isNullable_withFalse_createsNotNullType(): Unit = with(subject) {
        isNullable = false

        build()

        expectThat(typeSlot.captured)
            .not()
            .isNullable()
    }

    @Test
    fun isNullable_withTrue_createsNullableType(): Unit = with(subject) {
        isNullable = true

        build()

        expectThat(typeSlot.captured)
            .isNullable()
    }

    @Test
    fun initWith_withoutBeingCalled_notAddDefaultValue(): Unit = with(subject) {
        build()

        verify(exactly = 0) { spec.defaultValue(any<String>()) }
    }

    @Test
    fun initWith_addDefaultValue(): Unit = with(subject) {
        val expectedValue = "null"
        initWith = expectedValue

        build()

        verify(exactly = 1) { spec.defaultValue(expectedValue) }
    }
}