package dev.patron.dsl.specs.classes

import com.squareup.kotlinpoet.TypeSpec
import dev.patron.dsl.defaults.annotation.PatronAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.classes.PatronClassReceiver
import dev.patron.dsl.defaults.functions.PatronFunctionReceiver
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.classes.ClassReceiver
import dev.patron.dsl.interfaces.function.FunctionReceiver
import dev.patron.dsl.interfaces.visibility.VisibilityChanger

class ClassBuilder(spec: PatronClassSpec) :
    Builder<PatronClassSpec, TypeSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    Annotator by PatronAnnotator(spec),
    FunctionReceiver by PatronFunctionReceiver(spec),
    ClassReceiver by PatronClassReceiver(spec) {

    companion object {
        fun withSpec(name: String) = ClassBuilder(PatronClassSpec(name))
    }
}
