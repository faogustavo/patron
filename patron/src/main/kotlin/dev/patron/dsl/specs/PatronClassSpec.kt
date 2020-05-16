package dev.patron.dsl.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.delegates.VisibilityHandler
import dev.patron.dsl.interfaces.annotation.AnnotableSpec
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.classes.ReceivableClassSpec
import dev.patron.dsl.interfaces.enums.ReceivableEnumSpec
import dev.patron.dsl.interfaces.function.ReceivableFunctionSpec
import dev.patron.dsl.interfaces.objects.ReceivableObjectSpec
import dev.patron.dsl.interfaces.property.ReceivablePropertySpec
import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.Visibility

class PatronClassSpec(name: String) : Buildable<TypeSpec>, ChangeableVisibility,
    AnnotableSpec, ReceivableFunctionSpec, ReceivableClassSpec, ReceivableEnumSpec, ReceivableObjectSpec,
    ReceivablePropertySpec {

    private val specBuilder: TypeSpec.Builder = TypeSpec.classBuilder(name)

    override var visibility: Visibility by VisibilityHandler(specBuilder.modifiers)
    var isData: Boolean
        get() = specBuilder.modifiers.any { it == KModifier.DATA }
        set(value) {
            specBuilder.modifiers.remove(KModifier.DATA)
            if (value) {
                specBuilder.addModifiers(KModifier.DATA)
            }
        }

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
}
