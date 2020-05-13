package dev.patron.dsl.defaults.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import dev.patron.dsl.interfaces.annotation.Annotable

class PatronFileAnnotator(annotable: Annotable) : PatronAnnotator(annotable) {
    override val site = AnnotationSpec.UseSiteTarget.FILE
}
