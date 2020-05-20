package dev.patron.builders.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.defaults.building.PatronBuilder
import dev.patron.interfaces.building.Builder
import dev.patron.specs.PatronAnnotationSpec

class AnnotationBuilder(private val spec: PatronAnnotationSpec) :
    Builder<PatronAnnotationSpec, AnnotationSpec> by PatronBuilder(
        spec
    ) {

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
