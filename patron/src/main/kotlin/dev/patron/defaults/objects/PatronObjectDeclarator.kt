package dev.patron.defaults.objects

import dev.patron.interfaces.objects.ObjectDeclarator
import dev.patron.interfaces.objects.ObjectDeclaratorBlock
import dev.patron.interfaces.objects.ReceivableObjectSpec

class PatronObjectDeclarator(private val spec: ReceivableObjectSpec) :
    ObjectDeclarator {

    override fun objects(block: ObjectDeclaratorBlock) {
        ObjectDeclarator.Block(spec).apply(block)
    }
}
