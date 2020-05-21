package dev.patron.builders.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.defaults.building.PatronBuilder
import dev.patron.interfaces.building.Builder
import dev.patron.modifiers.AnnotationSite
import dev.patron.specs.PatronAnnotationSpec

class AnnotationBuilder(private val spec: PatronAnnotationSpec) :
    Builder<PatronAnnotationSpec, AnnotationSpec> by PatronBuilder(
        spec
    ) {

    infix fun String.withValue(value: Any) {
        spec.addParameter(this, value)
    }

    companion object {
        fun withSpec(
            type: ClassName,
            site: AnnotationSite = AnnotationSite.DEFAULT
        ) = AnnotationBuilder(
            PatronAnnotationSpec(
                type,
                site
            )
        )
    }
}
