package dev.patron.properties

import com.squareup.kotlinpoet.ClassName
import kotlin.reflect.KClass

abstract class PropertyBuilder {
    abstract fun addProperty(builder: PropertyItemBuilder)

    infix fun String.withType(type: ClassName) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).let(::addProperty)
    }

    fun String.withType(type: ClassName, block: PropertyItemBuilder.() -> Unit) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).apply(block).let(::addProperty)
    }

    infix fun String.withType(type: KClass<*>) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).let(::addProperty)
    }

    fun String.withType(type: KClass<*>, block: PropertyItemBuilder.() -> Unit) {
        PropertyItemBuilder(
            name = this,
            type = type
        ).apply(block).let(::addProperty)
    }
}
