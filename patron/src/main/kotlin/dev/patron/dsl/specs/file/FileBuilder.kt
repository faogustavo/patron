package dev.patron.dsl.specs.file

import com.squareup.kotlinpoet.FileSpec
import dev.patron.dsl.defaults.annotation.PatronFileAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder

class FileBuilder(spec: PatronFileSpec) :
    Builder<PatronFileSpec, FileSpec> by PatronBuilder(
        spec
    ),
    Annotator by PatronFileAnnotator(spec) {

    companion object {
        fun withSpec(
            fileName: String,
            packageName: String = ""
        ) = FileBuilder(
            PatronFileSpec(
                fileName,
                packageName
            )
        )
    }
}
