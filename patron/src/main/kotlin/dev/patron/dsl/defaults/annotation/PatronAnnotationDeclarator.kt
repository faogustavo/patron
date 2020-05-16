package dev.patron.dsl.defaults.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import dev.patron.dsl.interfaces.annotation.AnnotableSpec
import dev.patron.dsl.interfaces.annotation.AnnotationDeclarator
import dev.patron.dsl.interfaces.annotation.AnnotationDeclaratorBlock
import dev.patron.dsl.interfaces.annotation.Annotator

enum class AnnotationTarget(val target: AnnotationSpec.UseSiteTarget?) {
    DEFAULT(null),
    FILE(AnnotationSpec.UseSiteTarget.FILE)
}

class PatronAnnotationDeclarator(
    private val spec: AnnotableSpec,
    private val target: AnnotationTarget = AnnotationTarget.DEFAULT
) : AnnotationDeclarator {

    private val annotator: Annotator
        get() = when (target) {
            AnnotationTarget.FILE -> PatronFileAnnotator(spec)
            else -> PatronAnnotator(spec)
        }

    override fun annotations(block: AnnotationDeclaratorBlock) {
        AnnotationDeclarator.Block(annotator).apply(block)
    }
}
