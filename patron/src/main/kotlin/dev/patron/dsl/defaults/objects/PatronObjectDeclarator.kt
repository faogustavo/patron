package dev.patron.dsl.defaults.objects

import dev.patron.dsl.interfaces.objects.ObjectDeclarator
import dev.patron.dsl.interfaces.objects.ObjectDeclaratorBlock
import dev.patron.dsl.interfaces.objects.ReceivableObjectSpec

class PatronObjectDeclarator(private val spec: ReceivableObjectSpec) : ObjectDeclarator {

    override fun objects(block: ObjectDeclaratorBlock) {
        ObjectDeclarator.Block(spec).apply(block)
    }
}
