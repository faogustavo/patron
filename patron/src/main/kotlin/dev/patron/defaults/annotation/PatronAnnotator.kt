package dev.patron.defaults.annotation

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.annotation.AnnotationBuilder
import dev.patron.builders.annotation.AnnotationBuilderBlock
import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.annotation.Annotator
import dev.patron.modifiers.AnnotationSite

open class PatronAnnotator(
    private val annotableSpec: AnnotableSpec,
    private val site: AnnotationSite = AnnotationSite.DEFAULT
) : Annotator {

    override fun annotateWith(
        type: ClassName,
        block: AnnotationBuilderBlock
    ) = AnnotationBuilder
        .withSpec(type, site)
        .apply(block)
        .build()
        .let(annotableSpec::annotateWith)
}
