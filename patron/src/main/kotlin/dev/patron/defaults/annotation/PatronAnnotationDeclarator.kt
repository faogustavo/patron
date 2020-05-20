package dev.patron.defaults.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.annotation.AnnotationDeclaratorBlock
import dev.patron.interfaces.annotation.Annotator

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
            AnnotationTarget.FILE -> PatronFileAnnotator(
                spec
            )
            else -> PatronAnnotator(spec)
        }

    override fun annotations(block: AnnotationDeclaratorBlock) {
        AnnotationDeclarator.Block(annotator).apply(block)
    }
}
