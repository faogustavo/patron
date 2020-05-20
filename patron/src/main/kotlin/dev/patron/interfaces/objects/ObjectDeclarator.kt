package dev.patron.interfaces.objects

import com.squareup.kotlinpoet.ClassName
import dev.patron.Block
import dev.patron.builders.objects.ObjectBuilderBlock
import dev.patron.defaults.objects.PatronObjectReceiver

typealias ObjectDeclaratorBlock = Block<ObjectDeclarator.Block>

interface ObjectDeclarator {

    fun objects(block: ObjectDeclaratorBlock)

    class Block(spec: ReceivableObjectSpec) : ObjectReceiver by PatronObjectReceiver(
        spec
    ) {

        operator fun String.invoke(block: ObjectBuilderBlock) {
            newObject(
                name = this,
                block = block
            )
        }

        operator fun ClassName.invoke(block: ObjectBuilderBlock) {
            newObject(
                className = this,
                block = block
            )
        }
    }
}
