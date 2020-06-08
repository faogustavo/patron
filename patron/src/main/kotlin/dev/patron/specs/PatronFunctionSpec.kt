package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import dev.patron.delegates.VisibilityHandler
import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.building.Buildable
import dev.patron.interfaces.extension.Extensible
import dev.patron.interfaces.parameter.ReceivableParameter
import dev.patron.interfaces.returning.Returnable
import dev.patron.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.FunctionType
import dev.patron.modifiers.Visibility

class PatronFunctionSpec(
    name: String,
    type: FunctionType = FunctionType.DEFAULT
) : Buildable<FunSpec>,
    ChangeableVisibility,
    Returnable,
    AnnotableSpec,
    ReceivableParameter,
    Extensible {

    private val specBuilder: FunSpec.Builder = when (type) {
        FunctionType.CONSTRUCTOR -> FunSpec.constructorBuilder()
        FunctionType.GETTER -> FunSpec.getterBuilder()
        FunctionType.SETTER -> FunSpec.setterBuilder()
        else -> FunSpec.builder(name)
    }

    override var visibility: Visibility by VisibilityHandler(specBuilder.modifiers)

    override fun build() = specBuilder.build()

    override fun annotateWith(annotationSpec: AnnotationSpec) {
        specBuilder.addAnnotation(annotationSpec)
    }

    override fun withReturnType(typeName: TypeName) {
        specBuilder.returns(typeName)
    }

    override fun addParameter(parameterSpec: ParameterSpec) {
        specBuilder.addParameter(parameterSpec)
    }

    fun addCode(codeBlock: CodeBlock) {
        specBuilder.addCode(codeBlock)
    }

    override fun extend(className: ClassName) {
        specBuilder.receiver(className)
    }
}
