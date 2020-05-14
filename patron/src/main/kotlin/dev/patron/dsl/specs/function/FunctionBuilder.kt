package dev.patron.dsl.specs.function

import com.squareup.kotlinpoet.FunSpec
import dev.patron.dsl.defaults.annotation.PatronAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.returning.PatronReturner
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.returning.Returner
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.code.CodeBuilder
import dev.patron.dsl.specs.code.CodeBuilderBlock

open class FunctionBuilder(private val spec: PatronFunctionSpec) :
    Builder<PatronFunctionSpec, FunSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    Returner by PatronReturner(spec),
    Annotator by PatronAnnotator(spec) {

    fun code(block: CodeBuilderBlock) {
        CodeBuilder()
            .apply(block)
            .build()
            .let { spec.addCode(it) }
    }
}
