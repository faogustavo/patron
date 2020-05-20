package dev.patron.builders.classes

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.defaults.building.PatronBuilder
import dev.patron.defaults.classes.PatronClassDeclarator
import dev.patron.defaults.constructors.PatronConstructorDeclarator
import dev.patron.defaults.enums.PatronEnumDeclarator
import dev.patron.defaults.functions.PatronFunctionDeclarator
import dev.patron.defaults.objects.PatronCompanionObjectReceiver
import dev.patron.defaults.objects.PatronObjectDeclarator
import dev.patron.defaults.property.PatronPropertyDeclarator
import dev.patron.defaults.visibility.PatronVisibilityChanger
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.building.Builder
import dev.patron.interfaces.classes.ClassDeclarator
import dev.patron.interfaces.constructor.ConstructorDeclarator
import dev.patron.interfaces.enums.EnumDeclarator
import dev.patron.interfaces.function.FunctionDeclarator
import dev.patron.interfaces.objects.CompanionObjectReceiver
import dev.patron.interfaces.objects.ObjectDeclarator
import dev.patron.interfaces.property.PropertyDeclarator
import dev.patron.interfaces.visibility.VisibilityChanger
import dev.patron.specs.PatronClassSpec
import dev.patron.specs.PatronPropertySpec

class ClassBuilder(private val spec: PatronClassSpec) :
    Builder<PatronClassSpec, TypeSpec> by PatronBuilder(
        spec
    ),
    VisibilityChanger by PatronVisibilityChanger(
        spec
    ),
    AnnotationDeclarator by PatronAnnotationDeclarator(
        spec
    ),
    FunctionDeclarator by PatronFunctionDeclarator(
        spec
    ),
    ClassDeclarator by PatronClassDeclarator(
        spec
    ),
    EnumDeclarator by PatronEnumDeclarator(
        spec
    ),
    ObjectDeclarator by PatronObjectDeclarator(
        spec
    ),
    CompanionObjectReceiver by PatronCompanionObjectReceiver(
        spec
    ),
    PropertyDeclarator by PatronPropertyDeclarator(
        spec,
        PatronPropertySpec.Scope.CLASS
    ),
    ConstructorDeclarator by PatronConstructorDeclarator(
        spec
    ) {

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
