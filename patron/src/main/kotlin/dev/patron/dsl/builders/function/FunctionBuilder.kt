package dev.patron.dsl.builders.function

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import dev.patron.dsl.builders.code.CodeBuilder
import dev.patron.dsl.builders.code.CodeBuilderBlock
import dev.patron.dsl.builders.parameter.ParameterBuilderBlock
import dev.patron.dsl.defaults.annotation.PatronAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.parameter.PatronParameterReceiver
import dev.patron.dsl.defaults.returning.PatronReturner
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.parameter.ParameterReceiver
import dev.patron.dsl.interfaces.returning.Returner
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronFunctionSpec

open class FunctionBuilder(private val spec: PatronFunctionSpec) :
    Builder<PatronFunctionSpec, FunSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    Annotator by PatronAnnotator(spec) {

    fun code(block: CodeBuilderBlock) {
        CodeBuilder()
            .apply(block)
            .build()
            .let { spec.addCode(it) }
    }

    fun returning(block: FunctionReturnBlock) {
        Return().apply(block)
    }

    fun parameters(block: FunctionBuilderParametersBlock) {
        Parameters().apply(block)
    }

    inner class Parameters : ParameterReceiver by PatronParameterReceiver(spec) {
        operator fun Pair<String, ClassName>.invoke(block: ParameterBuilderBlock) {
            newParameter(
                name = this.first,
                type = this.second,
                block = block
            )
        }
    }

    inner class Return : Returner by PatronReturner(spec)

    companion object {
        fun withSpec(name: String) = FunctionBuilder(PatronFunctionSpec(name))
    }
}
