package dev.patron.dsl.interfaces.annotation

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.specs.annotation.AnnotationBuilderBlock

interface Annotator {
    fun annotateWith(type: ClassName, block: AnnotationBuilderBlock = {})
}
