package dev.patron.parameters.item

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import dev.patron.modifiers.Visibility
import dev.patron.parameters.item.tester.BaseParameterItemTester
import dev.patron.properties.PropertyItemBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.kotlinpoet.hasModifier

class ConstructorParameterItemBuilderTest : BaseParameterItemTester<ConstructorParameterItemBuilder>() {

    private val classSpec: TypeSpec.Builder = mockk()
    private val parameterName = "testParam"
    override val subject by lazy {
        ConstructorParameterItemBuilder(classSpec, funSpec, parameterName, String::class.asTypeName())
    }

    @Before
    override fun setUp() {
        super.setUp()

        mockkConstructor(PropertyItemBuilder::class)

        every { classSpec.addProperty(any()) } returns classSpec
    }

    @Test
    fun isProperty_withFalse_doesNotAddAsProperty(): Unit = with(subject) {
        isProperty = false

        verify(exactly = 0) { classSpec.addProperty(any()) }
    }

    @Test
    fun isProperty_withTrue_addsAsProperty(): Unit = with(subject) {
        isProperty = true

        verify(exactly = 1) { classSpec.addProperty(any()) }
    }

    @Test
    fun visibility_doesNotAddToModifiers(): Unit = with(subject) {
        val expectedVisibility = Visibility.PROTECTED

        visibility = expectedVisibility
        build()

        expectThat(modifiersSlot.captured)
            .not()
            .hasModifier(expectedVisibility.modifier)
    }

    @Test
    fun addPropertyToClass_withoutInitialValue_usesName(): Unit = with(subject) {
        isProperty = true

        verify(exactly = 1) { anyConstructed<PropertyItemBuilder>().initWith = parameterName }
    }

    @Test
    fun addPropertyToClass_withInitialValue_usesIt(): Unit = with(subject) {
        val expectedValue = "\"Some Value\""

        initWith = expectedValue
        isProperty = true

        verify(exactly = 1) { anyConstructed<PropertyItemBuilder>().initWith = expectedValue }
    }

    @Test
    fun addPropertyToClass_usesSetVisibility(): Unit = with(subject) {
        val expectedVisibility = Visibility.INTERNAL

        visibility = expectedVisibility
        isProperty = true

        verify(exactly = 1) { anyConstructed<PropertyItemBuilder>().visibility = expectedVisibility }
    }

    @Test
    fun addPropertyToClass_withNotNullType_setPropertyAsNotNull(): Unit = with(subject) {
        isNullable = false
        isProperty = true

        verify(exactly = 1) { anyConstructed<PropertyItemBuilder>().isNullable = false }
    }

    @Test
    fun addPropertyToClass_withNullableType_setPropertyAsNullable(): Unit = with(subject) {
        isNullable = true
        isProperty = true

        verify(exactly = 1) { anyConstructed<PropertyItemBuilder>().isNullable = true }
    }
}
