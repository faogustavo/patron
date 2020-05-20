package dev.patron.defaults.objects

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.objects.ObjectBuilder
import dev.patron.builders.objects.ObjectBuilderBlock
import dev.patron.ext.newClassName
import dev.patron.interfaces.objects.CompanionObjectReceiver
import dev.patron.interfaces.objects.ReceivableObjectSpec

class PatronCompanionObjectReceiver(private val receivableObjectSpec: ReceivableObjectSpec) :
    CompanionObjectReceiver {

    override fun companionObject(name: String, block: ObjectBuilderBlock) {
        ObjectBuilder.withCompanionSpec(name)
            .apply(block)
            .build()
            .let(receivableObjectSpec::addObject)
    }

    override fun companionObject(className: ClassName, block: ObjectBuilderBlock) =
        companionObject(className.newClassName, block)
}
