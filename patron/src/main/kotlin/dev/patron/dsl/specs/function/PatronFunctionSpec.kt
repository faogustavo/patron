package dev.patron.dsl.specs.function

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeName
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.returning.Returnable
import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.dsl.specs.annotation.AnnotationBuilder
import dev.patron.modifiers.Visibility

class PatronFunctionSpec(protected val specBuilder: FunSpec.Builder) :
    Buildable<FunSpec>, ChangeableVisibility, Returnable, Annotable {

    override fun build() = specBuilder.build()

    override fun updateVisibility(newVisibility: Visibility) {
        specBuilder.addModifiers(newVisibility.modifier)
    }

    override fun annotateWith(builder: AnnotationBuilder) {
        specBuilder.addAnnotation(builder.build())
    }

    override fun withReturnType(typeName: TypeName) {
        specBuilder.returns(typeName)
    }

    internal fun addCode(codeBlock: CodeBlock) {
        specBuilder.addCode(codeBlock)
    }

    companion object {
        fun withSpec(funName: String) = PatronFunctionSpec(
            FunSpec.builder(funName)
        )
    }
}
