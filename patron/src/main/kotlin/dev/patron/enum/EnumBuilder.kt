package dev.patron.enum

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import dev.patron.Builder
import dev.patron.annotation.AnnotationBuilder
import dev.patron.modifiers.Visibility
import kotlin.reflect.KClass

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

    fun annotateWith(
        clazz: KClass<*>,
        block: AnnotationBuilder.() -> Unit = {}
    ) = annotateWith(clazz.asClassName(), block)

    fun annotateWith(
        className: ClassName,
        block: AnnotationBuilder.() -> Unit = {}
    ) = AnnotationBuilder(className)
        .apply(block)
        .build()
        .also { spec.addAnnotation(it) }

    override fun build() = spec.build()
}
