package dev.patron.parameters.item

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import dev.patron.modifiers.Visibility
import dev.patron.properties.PropertyItemBuilder
import kotlin.reflect.KClass

class ConstructorParameterItemBuilder(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: ClassName
) : BaseClassParameterItemBuilder(classSpec, spec, name, type) {

    constructor(
        classSpec: TypeSpec.Builder,
        spec: FunSpec.Builder,
        name: String,
        type: KClass<*>
    ) : this(classSpec, spec, name, type.asClassName())

    var visibility: Visibility = Visibility.PUBLIC
    var isProperty: Boolean = false
        set(value) {
            field = value
            if (value) {
                addPropertyToClass()
            }
        }

    private fun addPropertyToClass() {
        PropertyItemBuilder(name, type).apply {
            initWith = this@ConstructorParameterItemBuilder.initWith ?: name
            visibility = this@ConstructorParameterItemBuilder.visibility
            isNullable = this@ConstructorParameterItemBuilder.isNullable
        }.build().let(classSpec::addProperty)
    }
}