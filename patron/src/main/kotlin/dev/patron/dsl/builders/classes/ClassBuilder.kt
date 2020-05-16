package dev.patron.dsl.builders.classes

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.defaults.annotation.PatronAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.classes.PatronClassReceiver
import dev.patron.dsl.defaults.enums.PatronEnumReceiver
import dev.patron.dsl.defaults.functions.PatronFunctionReceiver
import dev.patron.dsl.defaults.objects.PatronCompanionObjectReceiver
import dev.patron.dsl.defaults.objects.PatronObjectReceiver
import dev.patron.dsl.defaults.property.PatronPropertyReceiver
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.classes.ClassReceiver
import dev.patron.dsl.interfaces.enums.EnumReceiver
import dev.patron.dsl.interfaces.function.FunctionReceiver
import dev.patron.dsl.interfaces.objects.CompanionObjectReceiver
import dev.patron.dsl.interfaces.objects.ObjectReceiver
import dev.patron.dsl.interfaces.property.PropertyReceiver
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronClassSpec

class ClassBuilder(private val spec: PatronClassSpec) :
    Builder<PatronClassSpec, TypeSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    Annotator by PatronAnnotator(spec),
    FunctionReceiver by PatronFunctionReceiver(spec),
    ClassReceiver by PatronClassReceiver(spec),
    EnumReceiver by PatronEnumReceiver(spec),
    ObjectReceiver by PatronObjectReceiver(spec),
    CompanionObjectReceiver by PatronCompanionObjectReceiver(spec),
    PropertyReceiver by PatronPropertyReceiver(spec) {

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
