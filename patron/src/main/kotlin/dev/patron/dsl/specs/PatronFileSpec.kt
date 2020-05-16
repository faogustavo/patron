package dev.patron.dsl.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.classes.ReceivableClass
import dev.patron.dsl.interfaces.enums.ReceivableEnum
import dev.patron.dsl.interfaces.function.ReceivableFunction
import dev.patron.dsl.interfaces.objects.ReceivableObject
import dev.patron.dsl.interfaces.property.ReceivableProperty

class PatronFileSpec(
    fileName: String,
    packageName: String = ""
) : Buildable<FileSpec>, Annotable, ReceivableFunction, ReceivableClass, ReceivableEnum, ReceivableObject,
    ReceivableProperty {

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
