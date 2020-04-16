package dev.patron.properties

import com.squareup.kotlinpoet.*
import dev.patron.Builder
import dev.patron.functions.GetterBuilder
import dev.patron.functions.SetterBuilder
import dev.patron.modifiers.Visibility
import kotlin.reflect.KClass

abstract class PropertyBuilder {
    abstract fun <T : Any> addProperty(builder: PropertyItemBuilder<T>)

    infix fun <T : Any> String.withType(type: KClass<T>) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).let(::addProperty)
    }

    fun <T : Any> String.withType(type: KClass<T>, block: PropertyItemBuilder<T>.() -> Unit) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).apply(block).let(::addProperty)
    }
}

class LocalPropertyBuilder(protected val fileSpec: FileSpec.Builder) : PropertyBuilder() {
    override fun <T : Any> addProperty(builder: PropertyItemBuilder<T>) {
        fileSpec.addProperty(builder.build())
    }
}

class ClassPropertyBuilder(protected val typeSpec: TypeSpec.Builder) : PropertyBuilder() {
    override fun <T : Any> addProperty(builder: PropertyItemBuilder<T>) {
        typeSpec.addProperty(builder.build())
    }
}

class PropertyItemBuilder<T : Any>(
    protected val name: String,
    protected val type: KClass<T>
) : Builder<PropertySpec>() {

    var isMutable: Boolean = false
    var visibility: Visibility = Visibility.PUBLIC
    var initWith: String? = null
    var isNullable: Boolean = false

    private val builtType: TypeName
        get() = type.asTypeName().copy(nullable = isNullable)

    private var getter: GetterBuilder? = null
    private var setter: SetterBuilder? = null

    fun getter(block: GetterBuilder.() -> Unit) {
        getter = GetterBuilder(builtType).apply(block)
    }

    fun setter(block: SetterBuilder.() -> Unit) {
        setter = SetterBuilder(builtType).apply(block)
    }

    override fun build() = PropertySpec.builder(
        name = name,
        type = builtType
    ).apply {
        mutable(isMutable)
        initWith?.let { initializer(it) }
        addModifiers(visibility.modifier)
        getter?.let { getter(it.build()) }
        setter?.let { setter(it.build()) }
    }.build()
}