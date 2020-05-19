package dev.patron.dsl.builders.function

import com.squareup.kotlinpoet.FunSpec
import dev.patron.dsl.builders.code.CodeBuilder
import dev.patron.dsl.builders.code.CodeBuilderBlock
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.parameter.PatronParameterDeclarator
import dev.patron.dsl.defaults.returning.PatronReturner
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.parameter.ParameterDeclarator
import dev.patron.dsl.interfaces.returning.Returner
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronFunctionSpec

open class FunctionBuilder(private val spec: PatronFunctionSpec) :
    Builder<PatronFunctionSpec, FunSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    ParameterDeclarator by PatronParameterDeclarator(spec) {

    fun code(block: CodeBuilderBlock) {
        CodeBuilder()
            .apply(block)
            .build()
            .let { spec.addCode(it) }
    }

    fun returning(block: FunctionReturnBlock) {
        Return().apply(block)
    }

    inner class Return : Returner by PatronReturner(spec)

    companion object {
        fun withSpec(name: String) = FunctionBuilder(
            PatronFunctionSpec(
                name = name,
                type = PatronFunctionSpec.FunctionType.DEFAULT
            )
        )

        fun withConstructorSpec() = FunctionBuilder(
            PatronFunctionSpec(
                name = "",
                type = PatronFunctionSpec.FunctionType.CONSTRUCTOR
            )
        )
    }
}
