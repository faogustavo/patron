package dev.patron.builders.enums

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.defaults.building.PatronBuilder
import dev.patron.defaults.classes.PatronClassDeclarator
import dev.patron.defaults.enums.PatronEnumDeclarator
import dev.patron.defaults.functions.PatronFunctionReceiver
import dev.patron.defaults.objects.PatronCompanionObjectReceiver
import dev.patron.defaults.objects.PatronObjectDeclarator
import dev.patron.defaults.property.PatronPropertyDeclarator
import dev.patron.defaults.visibility.PatronVisibilityChanger
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.building.Builder
import dev.patron.interfaces.classes.ClassDeclarator
import dev.patron.interfaces.enums.EnumDeclarator
import dev.patron.interfaces.function.FunctionReceiver
import dev.patron.interfaces.objects.CompanionObjectReceiver
import dev.patron.interfaces.objects.ObjectDeclarator
import dev.patron.interfaces.property.PropertyDeclarator
import dev.patron.interfaces.visibility.VisibilityChanger
import dev.patron.specs.PatronEnumSpec
import dev.patron.specs.PatronPropertySpec

class EnumBuilder(private val spec: PatronEnumSpec) :
    Builder<PatronEnumSpec, TypeSpec> by PatronBuilder(
        spec
    ),
    AnnotationDeclarator by PatronAnnotationDeclarator(
        spec
    ),
    VisibilityChanger by PatronVisibilityChanger(
        spec
    ),
    FunctionReceiver by PatronFunctionReceiver(
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
        PatronPropertySpec.Scope.ENUM
    ) {

    fun values(block: SimpleEnumValueBlock) {
        ValueSetter().apply(block)
    }

    inner class ValueSetter {
        operator fun String.unaryMinus() {
            spec.addValue(this)
        }
    }

    companion object {
        fun withSpec(name: String) = EnumBuilder(
            PatronEnumSpec(name)
        )
    }
}
