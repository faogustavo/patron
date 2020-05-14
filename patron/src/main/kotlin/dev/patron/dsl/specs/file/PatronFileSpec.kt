package dev.patron.dsl.specs.file

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.function.ReceivableFunction
import dev.patron.dsl.specs.annotation.AnnotationBuilder

class PatronFileSpec(
    fileName: String,
    packageName: String = ""
) : Buildable<FileSpec>, Annotable, ReceivableFunction {
    private val specBuilder =
        FileSpec.builder(packageName, fileName)

    override fun build() = specBuilder.build()

    override fun annotateWith(builder: AnnotationBuilder) {
        specBuilder.addAnnotation(builder.build())
    }

    override fun addFunction(funSpec: FunSpec) {
        specBuilder.addFunction(funSpec)
    }
}
