package dev.patron.parameters.item

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.asClassName
import dev.patron.Builder
import dev.patron.annotation.AnnotationBuilder
import kotlin.reflect.KClass

abstract class BaseParameterItemBuilder(
    protected val funSpec: FunSpec.Builder,
    protected val name: String,
    protected val type: ClassName
) : Builder<ParameterSpec>() {
    protected val modifiers: MutableList<KModifier> = mutableListOf()
    private val builtAnnotations: MutableList<AnnotationSpec> = mutableListOf()

    var isNullable: Boolean = false
    var initWith: String? = null

    fun annotateWith(
        clazz: KClass<*>,
        block: AnnotationBuilder.() -> Unit = {}
    ) = annotateWith(clazz.asClassName(), block)

    fun annotateWith(
        className: ClassName,
        block: AnnotationBuilder.() -> Unit = {}
    ) {
        val annotation = AnnotationBuilder(className)
            .apply(block)
            .build()

        builtAnnotations.add(annotation)
    }

    override fun build() = ParameterSpec.builder(
        name = name,
        type = type.copy(nullable = isNullable),
        modifiers = modifiers
    ).apply {
        initWith?.let { this.defaultValue(it) }
        builtAnnotations.forEach { this.addAnnotation(it) }
    }.build()
}
