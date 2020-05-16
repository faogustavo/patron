package dev.patron.dsl.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import dev.patron.dsl.delegates.VisibilityHandler
import dev.patron.dsl.interfaces.annotation.AnnotableSpec
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.parameter.ReceivableParameter
import dev.patron.dsl.interfaces.returning.Returnable
import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.Visibility

class PatronFunctionSpec(name: String) : Buildable<FunSpec>, ChangeableVisibility, Returnable, AnnotableSpec,
    ReceivableParameter {
    private val specBuilder: FunSpec.Builder = FunSpec.builder(name)

    override var visibility: Visibility by VisibilityHandler(specBuilder.modifiers)

    override fun build() = specBuilder.build()

    override fun annotateWith(annotationSpec: AnnotationSpec) {
        specBuilder.addAnnotation(annotationSpec)
    }

    override fun withReturnType(typeName: TypeName) {
        specBuilder.returns(typeName)
    }

    internal fun addCode(codeBlock: CodeBlock) {
        specBuilder.addCode(codeBlock)
    }

    override fun addParameter(parameterSpec: ParameterSpec) {
        specBuilder.addParameter(parameterSpec)
    }
}
