package dev.patron.builders.function

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import dev.patron.builders.code.CodeBuilder
import dev.patron.builders.code.CodeBuilderBlock
import dev.patron.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.defaults.building.PatronBuilder
import dev.patron.defaults.parameter.PatronParameterDeclarator
import dev.patron.defaults.returning.PatronReturner
import dev.patron.defaults.visibility.PatronVisibilityChanger
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.building.Builder
import dev.patron.interfaces.parameter.ParameterDeclarator
import dev.patron.interfaces.returning.Returner
import dev.patron.interfaces.visibility.VisibilityChanger
import dev.patron.modifiers.FunctionType
import dev.patron.specs.PatronFunctionSpec

open class FunctionBuilder(private val spec: PatronFunctionSpec) :
    Builder<PatronFunctionSpec, FunSpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    ParameterDeclarator by PatronParameterDeclarator(spec),
    AnnotationDeclarator by PatronAnnotationDeclarator(spec) {

    fun code(block: CodeBuilderBlock) {
        CodeBuilder()
            .apply(block)
            .build()
            .let { spec.addCode(it) }
    }

    fun returning(block: FunctionReturnBlock) {
        Return().apply(block)
    }

    fun extend(className: ClassName) {
        spec.extend(className)
    }

    inner class Return : Returner by PatronReturner(
        spec
    )

    companion object {
        fun withSpec(name: String) = FunctionBuilder(
            PatronFunctionSpec(
                name = name,
                type = FunctionType.DEFAULT
            )
        )

        fun withConstructorSpec() = FunctionBuilder(
            PatronFunctionSpec(
                name = "",
                type = FunctionType.CONSTRUCTOR
            )
        )

        fun withGetterSpec() = FunctionBuilder(
            PatronFunctionSpec(
                name = "",
                type = FunctionType.GETTER
            )
        )

        fun withSetterSpec() = FunctionBuilder(
            PatronFunctionSpec(
                name = "",
                type = FunctionType.SETTER
            )
        )
    }
}
