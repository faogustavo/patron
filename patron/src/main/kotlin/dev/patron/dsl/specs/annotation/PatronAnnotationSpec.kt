package dev.patron.dsl.specs.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.interfaces.building.Buildable

class PatronAnnotationSpec(
    type: ClassName,
    site: AnnotationSpec.UseSiteTarget? = null
) : Buildable<AnnotationSpec> {
    internal val specBuilder = AnnotationSpec.builder(type).apply {
        site?.let(::useSiteTarget)
    }

    override fun build() = specBuilder.build()
}
