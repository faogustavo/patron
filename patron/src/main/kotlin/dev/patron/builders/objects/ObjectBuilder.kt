package dev.patron.builders.objects

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.defaults.building.PatronBuilder
import dev.patron.defaults.classes.PatronClassDeclarator
import dev.patron.defaults.enums.PatronEnumDeclarator
import dev.patron.defaults.functions.PatronFunctionDeclarator
import dev.patron.defaults.objects.PatronObjectDeclarator
import dev.patron.defaults.property.PatronPropertyDeclarator
import dev.patron.defaults.visibility.PatronVisibilityChanger
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.building.Builder
import dev.patron.interfaces.classes.ClassDeclarator
import dev.patron.interfaces.enums.EnumDeclarator
import dev.patron.interfaces.function.FunctionDeclarator
import dev.patron.interfaces.objects.ObjectDeclarator
import dev.patron.interfaces.property.PropertyDeclarator
import dev.patron.interfaces.visibility.VisibilityChanger
import dev.patron.specs.PatronObjectSpec
import dev.patron.specs.PatronPropertySpec

class ObjectBuilder(spec: PatronObjectSpec) :
    Builder<PatronObjectSpec, TypeSpec> by PatronBuilder(
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
    PropertyDeclarator by PatronPropertyDeclarator(
        spec,
        PatronPropertySpec.Scope.OBJECT
    ) {

    companion object {
        fun withSpec(name: String) = ObjectBuilder(
            PatronObjectSpec(
                name,
                isCompanion = false
            )
        )

        fun withCompanionSpec(name: String = "") = ObjectBuilder(
            PatronObjectSpec(
                name,
                isCompanion = true
            )
        )
    }
}
