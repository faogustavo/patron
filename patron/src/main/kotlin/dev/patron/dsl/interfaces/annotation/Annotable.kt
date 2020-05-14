package dev.patron.dsl.interfaces.annotation

import com.squareup.kotlinpoet.AnnotationSpec

interface Annotable {
    fun annotateWith(annotationSpec: AnnotationSpec)
}
