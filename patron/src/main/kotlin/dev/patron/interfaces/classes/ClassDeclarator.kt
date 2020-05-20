package dev.patron.interfaces.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.Block
import dev.patron.builders.classes.ClassBuilderBlock
import dev.patron.defaults.classes.PatronClassReceiver

typealias ClassDeclaratorBlock = Block<ClassDeclarator.Block>

interface ClassDeclarator {
    fun classes(block: ClassDeclaratorBlock)
    class Block(spec: ReceivableClassSpec) : ClassReceiver by PatronClassReceiver(
        spec
    ) {

        operator fun String.invoke(block: ClassBuilderBlock) {
            newClass(
                name = this,
                block = block
            )
        }

        operator fun ClassName.invoke(block: ClassBuilderBlock) {
            newClass(
                className = this,
                block = block
            )
        }
    }
}
