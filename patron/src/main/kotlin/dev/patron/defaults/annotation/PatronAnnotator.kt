package dev.patron.defaults.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.annotation.AnnotationBuilder
import dev.patron.builders.annotation.AnnotationBuilderBlock
import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.annotation.Annotator

open class PatronAnnotator(private val annotableSpec: AnnotableSpec) :
    Annotator {

    protected open val site: AnnotationSpec.UseSiteTarget? = null

    override fun annotateWith(
        type: ClassName,
        block: AnnotationBuilderBlock
    ) = AnnotationBuilder
        .withSpec(type, site)
        .apply(block)
        .build()
        .let(annotableSpec::annotateWith)
}
