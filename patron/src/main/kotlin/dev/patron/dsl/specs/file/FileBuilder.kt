package dev.patron.dsl.specs.file

import com.squareup.kotlinpoet.FileSpec
import dev.patron.dsl.defaults.annotation.PatronFileAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.functions.PatronFunctionReceiver
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.function.FunctionReceiver

class FileBuilder(spec: PatronFileSpec) :
    Builder<PatronFileSpec, FileSpec> by PatronBuilder(spec),
    Annotator by PatronFileAnnotator(spec),
    FunctionReceiver by PatronFunctionReceiver(spec) {

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
