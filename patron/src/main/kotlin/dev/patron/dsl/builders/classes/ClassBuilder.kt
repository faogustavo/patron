package dev.patron.dsl.builders.classes

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.classes.PatronClassDeclarator
import dev.patron.dsl.defaults.enums.PatronEnumDeclarator
import dev.patron.dsl.defaults.functions.PatronFunctionDeclarator
import dev.patron.dsl.defaults.objects.PatronCompanionObjectReceiver
import dev.patron.dsl.defaults.objects.PatronObjectDeclarator
import dev.patron.dsl.defaults.property.PatronPropertyDeclarator
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.AnnotationDeclarator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.classes.ClassDeclarator
import dev.patron.dsl.interfaces.enums.EnumDeclarator
import dev.patron.dsl.interfaces.function.FunctionDeclarator
import dev.patron.dsl.interfaces.objects.CompanionObjectReceiver
import dev.patron.dsl.interfaces.objects.ObjectDeclarator
import dev.patron.dsl.interfaces.property.PropertyDeclarator
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronClassSpec

class ClassBuilder(private val spec: PatronClassSpec) :
    Builder<PatronClassSpec, TypeSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    AnnotationDeclarator by PatronAnnotationDeclarator(spec),
    FunctionDeclarator by PatronFunctionDeclarator(spec),
    ClassDeclarator by PatronClassDeclarator(spec),
    EnumDeclarator by PatronEnumDeclarator(spec),
    ObjectDeclarator by PatronObjectDeclarator(spec),
    CompanionObjectReceiver by PatronCompanionObjectReceiver(spec),
    PropertyDeclarator by PatronPropertyDeclarator(spec) {

    var isData: Boolean
        get() = spec.isData
        set(value) {
            spec.isData = value
        }

    companion object {
        fun withSpec(name: String) = ClassBuilder(
            PatronClassSpec(name)
        )
    }
}
