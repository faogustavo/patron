package dev.patron.interfaces.annotation

import com.squareup.kotlinpoet.ClassName
import dev.patron.Block
import dev.patron.builders.annotation.AnnotationBuilderBlock

typealias AnnotationDeclaratorBlock = Block<AnnotationDeclarator.Block>

interface AnnotationDeclarator {

    fun annotations(block: AnnotationDeclaratorBlock)

    class Block(annotator: Annotator) : Annotator by annotator {
        operator fun ClassName.unaryMinus() {
            annotateWith(
                type = this
            )
        }

        operator fun ClassName.invoke(block: AnnotationBuilderBlock) {
            annotateWith(
                type = this,
                block = block
            )
        }
    }
}
