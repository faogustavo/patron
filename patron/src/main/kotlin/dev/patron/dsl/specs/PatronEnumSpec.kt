package dev.patron.dsl.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.delegates.VisibilityHandler
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.classes.ReceivableClass
import dev.patron.dsl.interfaces.enums.ReceivableEnumSpec
import dev.patron.dsl.interfaces.function.ReceivableFunctionSpec
import dev.patron.dsl.interfaces.objects.ReceivableObjectSpec
import dev.patron.dsl.interfaces.property.ReceivablePropertySpec
import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.Visibility

class PatronEnumSpec(
    enumName: String
) : Buildable<TypeSpec>, ChangeableVisibility, Annotable, ReceivableFunctionSpec, ReceivableClass, ReceivableEnumSpec,
    ReceivableObjectSpec, ReceivablePropertySpec {

    private val specBuilder: TypeSpec.Builder = TypeSpec.enumBuilder(enumName)

    override var visibility: Visibility by VisibilityHandler(specBuilder.modifiers)

    override fun build() = specBuilder.build()

    override fun annotateWith(annotationSpec: AnnotationSpec) {
        specBuilder.addAnnotation(annotationSpec)
    }

    override fun addFunction(funSpec: FunSpec) {
        specBuilder.addFunction(funSpec)
    }

    override fun addClass(typeSpec: TypeSpec) {
        specBuilder.addType(typeSpec)
    }

    override fun addEnum(typeSpec: TypeSpec) {
        specBuilder.addType(typeSpec)
    }

    override fun addObject(typeSpec: TypeSpec) {
        specBuilder.addType(typeSpec)
    }

    override fun addProperty(propertySpec: PropertySpec) {
        specBuilder.addProperty(propertySpec)
    }

    fun addValue(name: String) {
        specBuilder.addEnumConstant(name)
    }
}
