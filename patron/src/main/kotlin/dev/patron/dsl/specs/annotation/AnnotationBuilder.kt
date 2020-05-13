package dev.patron.dsl.specs.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.interfaces.building.Builder

class AnnotationBuilder(private val spec: PatronAnnotationSpec) :
    Builder<PatronAnnotationSpec, AnnotationSpec> by PatronBuilder(
        spec
    ) {

    infix fun String.withValue(value: Boolean) {
        spec.specBuilder.addMember(this.replaceFirst("%B", value.toString()))
    }

    infix fun String.withValue(value: Any) {
        spec.specBuilder.addMember(this, value)
    }

    companion object {
        fun withSpec(
            type: ClassName,
            site: AnnotationSpec.UseSiteTarget? = null
        ) = AnnotationBuilder(
            PatronAnnotationSpec(
                type,
                site
            )
        )
    }
}
