package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeName
import dev.patron.functions.testers.BaseFunctionTester
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SetterBuilderTest : BaseFunctionTester<SetterBuilder>() {

    private val type: TypeName = mockk()
    override val subject by lazy { SetterBuilder(type) }

    @Before
    override fun setUp() {
        super.setUp()

        mockkObject(FunSpec)
        mockkObject(FunSpec.Companion)

        every { FunSpec.setterBuilder() } returns spec
        every { spec.addParameter(any(), any<TypeName>()) } returns spec
    }

    @Test
    fun build_addParameterToSpec() {
        subject.build()

        verify(exactly = 1) { spec.addParameter("field", type) }
    }

    @Test
    fun name_addParameterToSpecWithGivenName() {
        val expectedName = "otherName"

        subject.name = expectedName
        subject.build()

        verify(exactly = 1) { spec.addParameter(expectedName, type) }
    }
}
