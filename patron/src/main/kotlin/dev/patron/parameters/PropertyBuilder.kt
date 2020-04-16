package dev.patron.parameters

import com.squareup.kotlinpoet.PropertySpec
import dev.patron.Builder
import dev.patron.properties.Visibility

class PropertyBuilder<T>(
    val name: String,
    val type: Class<T>
) : Builder<PropertySpec>() {
    protected val spec: PropertySpec.Builder = PropertySpec.builder(
        name = name,
        type = type
    )

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    fun initialize(fieldName: String = name) {
        spec.initializer(fieldName)
    }

    override fun build() = spec.build()
}