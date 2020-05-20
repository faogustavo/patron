package dev.patron.interfaces.annotation

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.annotation.AnnotationBuilderBlock

interface Annotator {
    fun annotateWith(type: ClassName, block: AnnotationBuilderBlock = {})
}
