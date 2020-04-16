package dev.patron.parameters

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.Builder
import dev.patron.modifiers.Visibility
import dev.patron.properties.PropertyItemBuilder
import kotlin.reflect.KClass

abstract class BaseParameterItemBuilder<T : Any>(
    protected val spec: FunSpec.Builder,
    protected val name: String,
    protected val type: KClass<T>
) : Builder<Unit>() {
    protected val modifiers: MutableList<KModifier> = mutableListOf()

    override fun build() {
        spec.addParameter(name, type, modifiers)
    }
}

abstract class BaseClassParameterItemBuilder<T : Any>(
    protected val classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: KClass<T>
) : BaseParameterItemBuilder<T>(spec, name, type)

class LocalFunctionParameterItemBuilder<T : Any>(
    spec: FunSpec.Builder,
    name: String,
    type: KClass<T>
) : BaseParameterItemBuilder<T>(spec, name, type) {

    var isVararg: Boolean = false
        set(value) {
            field = value
            modifiers.add(KModifier.VARARG)
        }

    fun invoke(block: LocalFunctionParameterItemBuilder<T>.() -> Unit) {
        apply(block)
    }
}

class FunctionParameterItemBuilder<T : Any>(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: KClass<T>
) : BaseClassParameterItemBuilder<T>(classSpec, spec, name, type) {

    var isVararg: Boolean = false
        set(value) {
            field = value
            modifiers.add(KModifier.VARARG)
        }

    fun invoke(block: FunctionParameterItemBuilder<T>.() -> Unit) {
        apply(block)
    }
}

class ConstructorParameterItemBuilder<T : Any>(
    classSpec: TypeSpec.Builder,
    spec: FunSpec.Builder,
    name: String,
    type: KClass<T>
) : BaseClassParameterItemBuilder<T>(classSpec, spec, name, type) {

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            modifiers.add(visibility.modifier)
        }

    var isProperty: Boolean = false
        set(value) {
            field = value
            if (value) {
                addPropertyToClass()
            }
        }

    operator fun invoke(block: BaseParameterItemBuilder<T>.() -> Unit) {
        apply(block)
    }

    private fun addPropertyToClass() {
        PropertyItemBuilder(name, type).run {
            visibility = this@ConstructorParameterItemBuilder.visibility
            initWith = name
            classSpec.addProperty(build())
        }
    }
}