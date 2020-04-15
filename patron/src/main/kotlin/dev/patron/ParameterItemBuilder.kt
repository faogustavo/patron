package dev.patron

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec

abstract class BaseParameterItemBuilder<T>(
    protected val classSpec: TypeSpec.Builder,
    protected val spec: FunSpec.Builder,
    protected val name: String,
    protected val type: Class<T>
)

class FunctionParameterItemBuilder<T>(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: Class<T>
) : BaseParameterItemBuilder<T>(classSpec, spec, name, type) {

    fun invoke(block: FunctionParameterItemBuilder<T>.() -> Unit) {
        apply(block)
    }
}

class ConstructorParameterItemBuilder<T>(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: Class<T>
) : BaseParameterItemBuilder<T>(classSpec, spec, name, type) {

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    var isProperty: Boolean = false
        set(value) {
            field = value
            if (value) { addPropertyToClass() }
        }

    operator fun invoke(block: BaseParameterItemBuilder<T>.() -> Unit) {
        apply(block)
    }

    private fun addPropertyToClass() {
        PropertyBuilder(name, type).run {
            this.visibility = this@ConstructorParameterItemBuilder.visibility
            initialize()
            classSpec.addProperty(build())
        }
    }
}