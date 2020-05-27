package dev.patron.builders.file

import com.squareup.kotlinpoet.FileSpec
import dev.patron.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.defaults.building.PatronBuilder
import dev.patron.defaults.classes.PatronClassDeclarator
import dev.patron.defaults.enums.PatronEnumDeclarator
import dev.patron.defaults.functions.PatronFunctionDeclarator
import dev.patron.defaults.objects.PatronObjectDeclarator
import dev.patron.defaults.property.PatronPropertyDeclarator
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.building.Builder
import dev.patron.interfaces.classes.ClassDeclarator
import dev.patron.interfaces.enums.EnumDeclarator
import dev.patron.interfaces.function.FunctionDeclarator
import dev.patron.interfaces.objects.ObjectDeclarator
import dev.patron.interfaces.property.PropertyDeclarator
import dev.patron.modifiers.AnnotationSite
import dev.patron.specs.PatronFileSpec
import dev.patron.specs.PatronPropertySpec

class FileBuilder(spec: PatronFileSpec) :
    Builder<PatronFileSpec, FileSpec> by PatronBuilder(spec),
    AnnotationDeclarator by PatronAnnotationDeclarator(spec, AnnotationSite.FILE),
    FunctionDeclarator by PatronFunctionDeclarator(spec),
    ClassDeclarator by PatronClassDeclarator(spec),
    EnumDeclarator by PatronEnumDeclarator(spec),
    ObjectDeclarator by PatronObjectDeclarator(spec),
    PropertyDeclarator by PatronPropertyDeclarator(spec, PatronPropertySpec.Scope.FILE) {

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
