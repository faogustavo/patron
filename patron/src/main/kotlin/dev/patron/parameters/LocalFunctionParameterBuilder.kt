package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import dev.patron.parameters.item.LocalFunctionParameterItemBuilder
import kotlin.reflect.KClass

class LocalFunctionParameterBuilder(
    spec: FunSpec.Builder
) : BaseParameterBuilder<LocalFunctionParameterItemBuilder>(spec) {

    override fun buildParameterItemBuilder(
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ): LocalFunctionParameterItemBuilder =
        LocalFunctionParameterItemBuilder(
            spec = spec,
            name = name,
            type = type
        )
}
