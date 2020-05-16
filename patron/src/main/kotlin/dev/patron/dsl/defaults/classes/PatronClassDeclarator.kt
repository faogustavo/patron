package dev.patron.dsl.defaults.classes

import dev.patron.dsl.interfaces.classes.ClassDeclarator
import dev.patron.dsl.interfaces.classes.ClassDeclaratorBlock
import dev.patron.dsl.interfaces.classes.ReceivableClassSpec

class PatronClassDeclarator(private val spec: ReceivableClassSpec) : ClassDeclarator {

    override fun classes(block: ClassDeclaratorBlock) {
        ClassDeclarator.Block(spec).apply(block)
    }
}
