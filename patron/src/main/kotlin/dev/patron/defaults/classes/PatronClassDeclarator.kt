package dev.patron.defaults.classes

import dev.patron.interfaces.classes.ClassDeclarator
import dev.patron.interfaces.classes.ClassDeclaratorBlock
import dev.patron.interfaces.classes.ReceivableClassSpec

class PatronClassDeclarator(private val spec: ReceivableClassSpec) :
    ClassDeclarator {

    override fun classes(block: ClassDeclaratorBlock) {
        ClassDeclarator.Block(spec).apply(block)
    }
}
