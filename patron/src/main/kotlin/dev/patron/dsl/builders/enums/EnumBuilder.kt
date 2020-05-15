package dev.patron.dsl.builders.enums

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.defaults.annotation.PatronFileAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronEnumSpec

class EnumBuilder(private val spec: PatronEnumSpec) :
    Builder<PatronEnumSpec, TypeSpec> by PatronBuilder(spec),
    Annotator by PatronFileAnnotator(spec),
    VisibilityChanger by PatronVisibilityChanger(spec) {

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
