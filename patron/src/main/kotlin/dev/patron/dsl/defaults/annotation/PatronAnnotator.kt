package dev.patron.dsl.defaults.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.interfaces.annotation.Annotable
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.specs.annotation.AnnotationBuilder
import dev.patron.dsl.specs.annotation.AnnotationBuilderBlock

open class PatronAnnotator(private val annotable: Annotable) : Annotator {

    protected open val site: AnnotationSpec.UseSiteTarget? = null

    override fun annotateWith(
        type: ClassName,
        block: AnnotationBuilderBlock
    ) = AnnotationBuilder
        .withSpec(type, site)
        .apply(block)
        .let(annotable::annotateWith)
}
