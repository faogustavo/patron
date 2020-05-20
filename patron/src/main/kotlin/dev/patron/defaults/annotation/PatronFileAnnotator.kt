package dev.patron.defaults.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import dev.patron.interfaces.annotation.AnnotableSpec

class PatronFileAnnotator(annotableSpec: AnnotableSpec) : PatronAnnotator(annotableSpec) {
    override val site = AnnotationSpec.UseSiteTarget.FILE
}
