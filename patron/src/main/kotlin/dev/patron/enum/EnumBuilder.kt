package dev.patron.enum

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.Builder
import dev.patron.modifiers.Visibility

class EnumBuilder(
    enumName: String
) : Builder<TypeSpec>() {
    private val spec: TypeSpec.Builder = TypeSpec.enumBuilder(enumName)

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    fun values(block: EnumValueBuilder.() -> Unit) {
        EnumValueBuilder(spec).apply(block)
    }

    override fun build() = spec.build()
}
