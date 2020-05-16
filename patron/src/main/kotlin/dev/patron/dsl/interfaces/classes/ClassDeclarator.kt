package dev.patron.dsl.interfaces.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.Block
import dev.patron.dsl.builders.classes.ClassBuilderBlock
import dev.patron.dsl.defaults.classes.PatronClassReceiver

typealias ClassDeclaratorBlock = Block<ClassDeclarator.Block>

interface ClassDeclarator {
    fun classes(block: ClassDeclaratorBlock)
    class Block(spec: ReceivableClassSpec) : ClassReceiver by PatronClassReceiver(spec) {

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
