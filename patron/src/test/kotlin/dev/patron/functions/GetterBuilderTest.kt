package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import dev.patron.functions.testers.BaseFunctionTester
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before

class GetterBuilderTest : BaseFunctionTester<GetterBuilder>() {

    override val subject by lazy { GetterBuilder() }

    @Before
    override fun setUp() {
        super.setUp()

        mockkObject(FunSpec)

        every { FunSpec.getterBuilder() } returns spec
    }
}
