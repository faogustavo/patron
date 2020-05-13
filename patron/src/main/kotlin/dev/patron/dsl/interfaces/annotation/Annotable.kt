package dev.patron.dsl.interfaces.annotation

import dev.patron.dsl.specs.annotation.AnnotationBuilder

interface Annotable {
    fun annotateWith(builder: AnnotationBuilder)
}
