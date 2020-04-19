package dev.patron.parameters.item

import dev.patron.parameters.item.tester.BaseParameterItemTester
import org.junit.Test
import strikt.api.expectThat
import strikt.kotlinpoet.isVarargs

class LocalFunctionParameterItemBuilderTest : BaseParameterItemTester<LocalFunctionParameterItemBuilder>() {

    override val subject by lazy {
        LocalFunctionParameterItemBuilder(funSpec, "testParam", String::class)
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
