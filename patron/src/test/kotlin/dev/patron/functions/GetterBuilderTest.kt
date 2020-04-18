package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before

class GetterBuilderTest : BaseFunctionBuilderTest() {

    @Before
    override fun setUp() {
        super.setUp()

        mockkObject(FunSpec)
        mockkObject(FunSpec)

        every { FunSpec.getterBuilder() } returns spec
    }
}
