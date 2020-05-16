package dev.patron.dsl.defaults.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import dev.patron.dsl.interfaces.annotation.AnnotableSpec

class PatronFileAnnotator(annotableSpec: AnnotableSpec) : PatronAnnotator(annotableSpec) {
    override val site = AnnotationSpec.UseSiteTarget.FILE
}
