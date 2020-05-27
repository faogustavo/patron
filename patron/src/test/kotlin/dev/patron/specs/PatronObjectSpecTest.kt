package dev.patron.specs

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PatronObjectSpecTest {

    private val builder = mockk<TypeSpec.Builder>()
    private val modifiers = mutableSetOf<KModifier>()

    @Before
    fun setUp() {
        mockkObject(TypeSpec)

        every { TypeSpec.objectBuilder(any<String>()) } returns builder
        every { TypeSpec.companionObjectBuilder(any()) } returns builder

        every { builder.build() } returns mockk()
        every { builder.modifiers } returns modifiers
    }

    @Test
    fun init_withCompanionSpec_returnsCompanionBuilder() {
        val expectedName: String = "ObjectCompanion"

        testSpec(name = expectedName, isCompanion = true)

        verify(exactly = 1) { TypeSpec.companionObjectBuilder(expectedName) }
        verify(exactly = 0) { TypeSpec.objectBuilder(any<String>()) }
    }

    @Test
    fun init_withCompanionSpecWithoutName_returnsCompanionBuilder() {
        testSpec(name = "", isCompanion = true)

        verify(exactly = 1) { TypeSpec.companionObjectBuilder(null) }
        verify(exactly = 0) { TypeSpec.objectBuilder(any<String>()) }
    }

    private fun testSpec(
        name: String = "SomeObject",
        isCompanion: Boolean = false
    ) = PatronObjectSpec(name, isCompanion)
}
