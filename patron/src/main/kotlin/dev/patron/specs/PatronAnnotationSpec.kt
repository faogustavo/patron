package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.interfaces.building.Buildable
import dev.patron.modifiers.AnnotationSite

class PatronAnnotationSpec(
    type: ClassName,
    site: AnnotationSite = AnnotationSite.DEFAULT
) : Buildable<AnnotationSpec> {
    private val specBuilder = AnnotationSpec.builder(type).apply {
        site.specSite?.let(::useSiteTarget)
    }

    fun addParameter(value: String, vararg args: Any) {
        specBuilder.addMember(value, *args)
    }

    override fun build() = specBuilder.build()
}
