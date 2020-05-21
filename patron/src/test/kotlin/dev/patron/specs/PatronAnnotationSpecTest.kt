package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.LITERAL_MARKER
import dev.patron.modifiers.AnnotationSite
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PatronAnnotationSpecTest {

    private val builder = mockk<AnnotationSpec.Builder>()

    @Before
    fun setUp() {
        mockkObject(AnnotationSpec)

        every { AnnotationSpec.builder(any<ClassName>()) } returns builder
        every { builder.useSiteTarget(any()) } returns builder
        every { builder.addMember(any(), *anyVararg()) } returns builder
        every { builder.build() } returns mockk()
    }

    @Test
    fun init_withDefaultSite_shouldNotCallUseSite() {
        val expectedSite = AnnotationSite.DEFAULT

        testSpec(expectedSite)

        verify(exactly = 0) { builder.useSiteTarget(expectedSite.specSite) }
    }

    @Test
    fun init_withNotDefaultSite_shouldCallUseSite() {
        val expectedSite = AnnotationSite.FILE

        testSpec(expectedSite).build()

        verify(exactly = 1) { builder.useSiteTarget(any()) }
    }

    @Test
    fun addParameter_withoutArgs_addMemberToBuilder() {
        val expectedValue = "X-Patron-Test=true"

        val spec = testSpec()
        spec.addParameter(expectedValue)

        verify(exactly = 1) { builder.addMember(expectedValue) }
    }

    @Test
    fun addParameter_withArgs_addMemberToBuilder() {
        val expectedValue = "X-Patron-Test=$LITERAL_MARKER"
        val expectedArgs: Array<Any> = arrayOf(true)

        val spec = testSpec()
        spec.addParameter(expectedValue, *expectedArgs)

        verify(exactly = 1) { builder.addMember(expectedValue, *expectedArgs) }
    }

    @Test
    fun build_shouldCallBuilderMethod() {
        testSpec().build()

        verify(exactly = 1) { builder.build() }
    }

    private fun testSpec() = PatronAnnotationSpec(
        ClassName("dev.patron.spec", "Test")
    )

    private fun testSpec(
        site: AnnotationSite = AnnotationSite.DEFAULT
    ) = PatronAnnotationSpec(
        type = ClassName("dev.patron.spec", "Test"),
        site = site
    )
}
