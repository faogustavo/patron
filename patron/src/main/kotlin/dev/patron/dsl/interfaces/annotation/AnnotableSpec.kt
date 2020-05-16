package dev.patron.dsl.interfaces.annotation

import com.squareup.kotlinpoet.AnnotationSpec

interface AnnotableSpec {
    fun annotateWith(annotationSpec: AnnotationSpec)
}