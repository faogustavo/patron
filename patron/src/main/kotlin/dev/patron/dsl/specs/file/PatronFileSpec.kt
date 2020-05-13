package dev.patron.dsl.specs.file

import com.squareup.kotlinpoet.FileSpec
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.specs.annotation.AnnotationBuilder

class PatronFileSpec(
    fileName: String,
    packageName: String = ""
) : Buildable<FileSpec>, Annotable {
    private val specBuilder =
        FileSpec.builder(packageName, fileName)

    override fun build() = specBuilder.build()

    override fun annotateWith(builder: AnnotationBuilder) {
        specBuilder.addAnnotation(builder.build())
    }
}
