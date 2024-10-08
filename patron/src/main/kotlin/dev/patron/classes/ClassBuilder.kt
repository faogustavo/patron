package dev.patron.classes

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import dev.patron.Builder
import dev.patron.annotation.AnnotationBuilder
import dev.patron.functions.ConstructorBuilder
import dev.patron.functions.FunctionBuilder
import dev.patron.modifiers.Visibility
import dev.patron.properties.ClassPropertyBuilder
import kotlin.reflect.KClass

class ClassBuilder(
    className: String
) : Builder<TypeSpec>() {
    protected val spec: TypeSpec.Builder = TypeSpec.classBuilder(className)

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    var isData: Boolean = false
        set(value) {
            field = value
            if (value) {
                spec.addModifiers(KModifier.DATA)
            }
        }

    fun primaryConstructor(block: ConstructorBuilder.() -> Unit) {
        ConstructorBuilder(classSpec = spec)
            .apply(block)
            .build()
            .run(spec::primaryConstructor)
    }

    fun properties(block: ClassPropertyBuilder.() -> Unit) {
        ClassPropertyBuilder(spec).apply(block)
    }

    fun function(name: String, block: FunctionBuilder.() -> Unit) {
        FunctionBuilder(classSpec = spec, name = name)
            .apply(block)
            .build()
            .run(spec::addFunction)
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
