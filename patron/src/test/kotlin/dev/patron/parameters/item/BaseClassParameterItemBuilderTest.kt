package dev.patron.parameters.item

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import dev.patron.parameters.item.tester.BaseParameterItemTester
import io.mockk.mockk

class BaseClassParameterItemBuilderTest : BaseParameterItemTester<BaseClassParameterItemBuilder>() {

    private val classSpec: TypeSpec.Builder = mockk()

    override val subject by lazy {
        object : BaseClassParameterItemBuilder(classSpec, funSpec, "testParam", String::class.asTypeName()) {}
    }
}
