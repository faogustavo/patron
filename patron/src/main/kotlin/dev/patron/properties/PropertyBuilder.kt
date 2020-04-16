package dev.patron.properties

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import dev.patron.Builder
import dev.patron.modifiers.Visibility

abstract class PropertyBuilder {
    abstract fun <T> addProperty(builder: PropertyItemBuilder<T>)

    infix fun <T> String.withType(type: Class<T>) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).let(::addProperty)
    }

    fun <T> String.withType(type: Class<T>, block: PropertyItemBuilder<T>.() -> Unit) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).apply(block).let(::addProperty)
    }
}

class LocalPropertyBuilder(protected val fileSpec: FileSpec.Builder) : PropertyBuilder() {
    override fun <T> addProperty(builder: PropertyItemBuilder<T>) {
        fileSpec.addProperty(builder.build())
    }
}

class ClassPropertyBuilder(protected val typeSpec: TypeSpec.Builder) : PropertyBuilder() {
    override fun <T> addProperty(builder: PropertyItemBuilder<T>) {
        typeSpec.addProperty(builder.build())
    }
}

class PropertyItemBuilder<T>(
    protected val name: String,
    protected val type: Class<T>
) : Builder<PropertySpec>() {

    var isMutable: Boolean = false
    var visibility: Visibility = Visibility.PUBLIC
    var initWith: String? = null
    var isNullable: Boolean = false

    override fun build() = PropertySpec.builder(
        name = name,
        type = type.asTypeName().copy(nullable = isNullable)
    ).apply {
        mutable(isMutable)
        initWith?.let { initializer(it) }
        addModifiers(visibility.modifier)
    }.build()
}