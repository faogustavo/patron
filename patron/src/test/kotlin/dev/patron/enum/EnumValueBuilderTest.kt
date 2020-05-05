package dev.patron.enum

import com.squareup.kotlinpoet.TypeSpec
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class EnumValueBuilderTest {

    private val spec: TypeSpec.Builder = mockk()
    private val subject by lazy { EnumValueBuilder(spec) }

    @Before
    fun setUp() {
        every { spec.addEnumConstant(any(), any()) } returns spec
    }

    @Test
    fun add_addEnumConstant(): Unit = with(subject) {
        add("EnumConstant1")
        add("EnumConstant2")
        add("EnumConstant3")

        io.mockk.verify(ordering = io.mockk.Ordering.SEQUENCE) {
            spec.addEnumConstant("EnumConstant1", any())
            spec.addEnumConstant("EnumConstant2", any())
            spec.addEnumConstant("EnumConstant3", any())
        }
    }

    @Test
    fun stringUnaryPlus_addEnumConstant(): Unit = with(subject) {
        +"EnumConstant1"
        +"EnumConstant2"
        +"EnumConstant3"

        io.mockk.verify(ordering = io.mockk.Ordering.SEQUENCE) {
            spec.addEnumConstant("EnumConstant1", any())
            spec.addEnumConstant("EnumConstant2", any())
            spec.addEnumConstant("EnumConstant3", any())
        }
    }
}
