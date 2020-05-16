package dev.patron.dsl.builders.objects

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.defaults.annotation.PatronAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.classes.PatronClassDeclarator
import dev.patron.dsl.defaults.enums.PatronEnumDeclarator
import dev.patron.dsl.defaults.functions.PatronFunctionDeclarator
import dev.patron.dsl.defaults.objects.PatronObjectDeclarator
import dev.patron.dsl.defaults.property.PatronPropertyDeclarator
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.classes.ClassDeclarator
import dev.patron.dsl.interfaces.enums.EnumDeclarator
import dev.patron.dsl.interfaces.function.FunctionDeclarator
import dev.patron.dsl.interfaces.objects.ObjectDeclarator
import dev.patron.dsl.interfaces.property.PropertyDeclarator
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronObjectSpec

class ObjectBuilder(spec: PatronObjectSpec) :
    Builder<PatronObjectSpec, TypeSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    Annotator by PatronAnnotator(spec),
    FunctionDeclarator by PatronFunctionDeclarator(spec),
    ClassDeclarator by PatronClassDeclarator(spec),
    EnumDeclarator by PatronEnumDeclarator(spec),
    ObjectDeclarator by PatronObjectDeclarator(spec),
    PropertyDeclarator by PatronPropertyDeclarator(spec) {

    companion object {
        fun withSpec(name: String) = ObjectBuilder(PatronObjectSpec(name, isCompanion = false))
        fun withCompanionSpec(name: String = "") = ObjectBuilder(PatronObjectSpec(name, isCompanion = true))
    }
}
