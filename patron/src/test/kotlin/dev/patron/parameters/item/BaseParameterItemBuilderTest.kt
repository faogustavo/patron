package dev.patron.parameters.item

import com.squareup.kotlinpoet.asTypeName
import dev.patron.parameters.item.tester.BaseParameterItemTester

class BaseParameterItemBuilderTest : BaseParameterItemTester<BaseParameterItemBuilder>() {

    override val subject by lazy {
        object : BaseParameterItemBuilder(funSpec, "testParam", String::class.asTypeName()) {}
    }
}
