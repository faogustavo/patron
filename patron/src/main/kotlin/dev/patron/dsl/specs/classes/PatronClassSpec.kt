package dev.patron.dsl.specs.classes

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.classes.ReceivableClass
import dev.patron.dsl.interfaces.function.ReceivableFunction
import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.Visibility

class PatronClassSpec(name: String) : Buildable<TypeSpec>, ChangeableVisibility,
    Annotable, ReceivableFunction, ReceivableClass {

    private val specBuilder: TypeSpec.Builder = TypeSpec.classBuilder(name)

    override fun build() = specBuilder.build()

    override fun updateVisibility(newVisibility: Visibility) {
        specBuilder.addModifiers(newVisibility.modifier)
    }

    override fun annotateWith(annotationSpec: AnnotationSpec) {
        specBuilder.addAnnotation(annotationSpec)
    }

    override fun addFunction(funSpec: FunSpec) {
        specBuilder.addFunction(funSpec)
    }

    override fun addClass(typeSpec: TypeSpec) {
        specBuilder.addType(typeSpec)
    }
}
