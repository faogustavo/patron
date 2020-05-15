package dev.patron.dsl.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.Visibility

class PatronEnumSpec(
    enumName: String
) : Buildable<TypeSpec>, ChangeableVisibility, Annotable {
    private val specBuilder: TypeSpec.Builder = TypeSpec.enumBuilder(enumName)

    override fun build() = specBuilder.build()

    override fun updateVisibility(newVisibility: Visibility) {
        specBuilder.addModifiers(newVisibility.modifier)
    }

    override fun annotateWith(annotationSpec: AnnotationSpec) {
        specBuilder.addAnnotation(annotationSpec)
    }

    fun addValue(name: String) {
        specBuilder.addEnumConstant(name)
    }
}
