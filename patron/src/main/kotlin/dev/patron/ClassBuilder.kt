package dev.patron

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

class ClassBuilder(
    className: String
) : Builder<TypeSpec>() {
    protected val spec: TypeSpec.Builder = TypeSpec.classBuilder(className)

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    fun primaryConstructor(block: ConstructorBuilder.() -> Unit) {
        ConstructorBuilder(classSpec = spec)
            .apply(block)
            .build()
            .run(spec::primaryConstructor)
    }

    override fun build() = spec.build()
}