package dev.patron.parameters.item

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.item.tester.BaseParameterItemTester
import io.mockk.mockk
import org.junit.Test
import strikt.api.expectThat
import strikt.kotlinpoet.isVarargs

class FunctionParameterItemBuilderTest : BaseParameterItemTester<FunctionParameterItemBuilder>() {

    private val classSpec: TypeSpec.Builder = mockk()
    override val subject by lazy {
        FunctionParameterItemBuilder(classSpec, funSpec, "testParam", String::class)
    }

    @Test
    fun isVararg_withFalse_doesNotVarargModifier(): Unit = with(subject) {
        isVararg = false

        build()

        expectThat(modifiersSlot.captured)
            .not()
            .isVarargs()
    }

    @Test
    fun isVararg_withTrue_addsVarargModifier(): Unit = with(subject) {
        isVararg = true

        build()

        expectThat(modifiersSlot.captured)
            .isVarargs()
    }
}
