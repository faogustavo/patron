package dev.patron.dsl.builders.enums

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.defaults.annotation.PatronFileAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.classes.PatronClassDeclarator
import dev.patron.dsl.defaults.enums.PatronEnumDeclarator
import dev.patron.dsl.defaults.functions.PatronFunctionReceiver
import dev.patron.dsl.defaults.objects.PatronCompanionObjectReceiver
import dev.patron.dsl.defaults.objects.PatronObjectDeclarator
import dev.patron.dsl.defaults.property.PatronPropertyDeclarator
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.classes.ClassDeclarator
import dev.patron.dsl.interfaces.enums.EnumDeclarator
import dev.patron.dsl.interfaces.function.FunctionReceiver
import dev.patron.dsl.interfaces.objects.CompanionObjectReceiver
import dev.patron.dsl.interfaces.objects.ObjectDeclarator
import dev.patron.dsl.interfaces.property.PropertyDeclarator
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronEnumSpec

class EnumBuilder(private val spec: PatronEnumSpec) :
    Builder<PatronEnumSpec, TypeSpec> by PatronBuilder(spec),
    Annotator by PatronFileAnnotator(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    FunctionReceiver by PatronFunctionReceiver(spec),
    ClassDeclarator by PatronClassDeclarator(spec),
    EnumDeclarator by PatronEnumDeclarator(spec),
    ObjectDeclarator by PatronObjectDeclarator(spec),
    CompanionObjectReceiver by PatronCompanionObjectReceiver(spec),
    PropertyDeclarator by PatronPropertyDeclarator(spec) {

    fun values(block: SimpleEnumValueBlock) {
        ValueSetter().apply(block)
    }

    inner class ValueSetter {
        operator fun String.unaryMinus() {
            spec.addValue(this)
        }
    }

    companion object {
        fun withSpec(name: String) = EnumBuilder(PatronEnumSpec(name))
    }
}
