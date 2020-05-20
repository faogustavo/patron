package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.building.Buildable
import dev.patron.interfaces.classes.ReceivableClassSpec
import dev.patron.interfaces.enums.ReceivableEnumSpec
import dev.patron.interfaces.function.ReceivableFunctionSpec
import dev.patron.interfaces.objects.ReceivableObjectSpec
import dev.patron.interfaces.property.ReceivablePropertySpec

class PatronFileSpec(
    fileName: String,
    packageName: String = ""
) : Buildable<FileSpec>,
    AnnotableSpec,
    ReceivableFunctionSpec,
    ReceivableClassSpec,
    ReceivableEnumSpec,
    ReceivableObjectSpec,
    ReceivablePropertySpec {

    private val specBuilder =
        FileSpec.builder(packageName, fileName)

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
