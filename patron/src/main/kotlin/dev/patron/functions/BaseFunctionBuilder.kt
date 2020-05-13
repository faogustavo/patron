package dev.patron.functions

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asClassName
import dev.patron.Builder
import dev.patron.annotation.AnnotationBuilder
import dev.patron.modifiers.Visibility
import dev.patron.statement.StatementBuilder
import java.io.File
import kotlin.reflect.KClass

abstract class BaseFunctionBuilder(
    protected val spec: FunSpec.Builder
) : Builder<FunSpec>() {

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    fun statements(block: StatementBuilder.() -> Unit) {
        StatementBuilder(spec = spec).apply(block)
    }

    fun codeFrom(code: String) {
        spec.addCode(code)
    }

    fun codeFrom(file: File) {
        spec.addCode(file.readText())
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
