package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.item.FunctionParameterItemBuilder
import kotlin.reflect.KClass

class FunctionParameterBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseClassParameterBuilder<FunctionParameterItemBuilder>(classSpec, spec) {

    override fun buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ): FunctionParameterItemBuilder =
        FunctionParameterItemBuilder(
            classSpec = classSpec,
            spec = spec,
            name = name,
            type = type
        )
}
