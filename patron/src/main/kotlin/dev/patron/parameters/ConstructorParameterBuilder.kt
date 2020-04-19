package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.parameters.item.ConstructorParameterItemBuilder
import kotlin.reflect.KClass

class ConstructorParameterBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder
) : BaseClassParameterBuilder<ConstructorParameterItemBuilder>(classSpec, spec) {

    override fun buildParameterItemBuilder(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ) = ConstructorParameterItemBuilder(
        classSpec = classSpec,
        spec = spec,
        name = name,
        type = type
    )
}
