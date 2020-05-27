package dev.patron.defaults.annotation

import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.annotation.AnnotationDeclaratorBlock
import dev.patron.modifiers.AnnotationSite

class PatronAnnotationDeclarator(
    private val spec: AnnotableSpec,
    private val site: AnnotationSite = AnnotationSite.DEFAULT
) : AnnotationDeclarator {

    override fun annotations(block: AnnotationDeclaratorBlock) {
        AnnotationDeclarator.Block(PatronAnnotator(spec, site)).apply(block)
    }
}
