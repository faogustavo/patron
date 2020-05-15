package dev.patron.dsl.defaults.objects

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.objects.ObjectBuilder
import dev.patron.dsl.builders.objects.ObjectBuilderBlock
import dev.patron.dsl.interfaces.objects.CompanionObjectReceiver
import dev.patron.dsl.interfaces.objects.ReceivableObject
import dev.patron.ext.newClassName

class PatronCompanionObjectReceiver(private val receivableObject: ReceivableObject) : CompanionObjectReceiver {

    override fun companionObject(name: String, block: ObjectBuilderBlock) {
        ObjectBuilder.withCompanionSpec(name)
            .apply(block)
            .build()
            .let(receivableObject::addObject)
    }

    override fun companionObject(className: ClassName, block: ObjectBuilderBlock) =
        companionObject(className.newClassName, block)
}
