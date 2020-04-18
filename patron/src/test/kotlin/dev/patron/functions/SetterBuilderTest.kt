package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeName
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SetterBuilderTest : BaseFunctionBuilderTest() {

    private val type: TypeName = mockk()
    private val setterSubject by lazy { SetterBuilder(type) }
    override val subject
        get() = setterSubject

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
        setterSubject.build()

        verify(exactly = 1) { spec.addParameter("field", type) }
    }

    @Test
    fun name_addParameterToSpecWithGivenName() {
        val expectedName = "otherName"

        setterSubject.name = expectedName
        setterSubject.build()

        verify(exactly = 1) { spec.addParameter(expectedName, type) }
    }
}
