package dev.patron.dsl.builders.file

import com.squareup.kotlinpoet.FileSpec
import dev.patron.dsl.defaults.annotation.AnnotationTarget
import dev.patron.dsl.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.classes.PatronClassDeclarator
import dev.patron.dsl.defaults.enums.PatronEnumDeclarator
import dev.patron.dsl.defaults.functions.PatronFunctionDeclarator
import dev.patron.dsl.defaults.objects.PatronObjectDeclarator
import dev.patron.dsl.defaults.property.PatronPropertyDeclarator
import dev.patron.dsl.interfaces.annotation.AnnotationDeclarator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.classes.ClassDeclarator
import dev.patron.dsl.interfaces.enums.EnumDeclarator
import dev.patron.dsl.interfaces.function.FunctionDeclarator
import dev.patron.dsl.interfaces.objects.ObjectDeclarator
import dev.patron.dsl.interfaces.property.PropertyDeclarator
import dev.patron.dsl.specs.PatronFileSpec

class FileBuilder(spec: PatronFileSpec) :
    Builder<PatronFileSpec, FileSpec> by PatronBuilder(spec),
    AnnotationDeclarator by PatronAnnotationDeclarator(spec, AnnotationTarget.FILE),
    FunctionDeclarator by PatronFunctionDeclarator(spec),
    ClassDeclarator by PatronClassDeclarator(spec),
    EnumDeclarator by PatronEnumDeclarator(spec),
    ObjectDeclarator by PatronObjectDeclarator(spec),
    PropertyDeclarator by PatronPropertyDeclarator(spec) {

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
