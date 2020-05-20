package dev.patron.defaults.objects

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.objects.ObjectBuilder
import dev.patron.builders.objects.ObjectBuilderBlock
import dev.patron.ext.newClassName
import dev.patron.interfaces.objects.ObjectReceiver
import dev.patron.interfaces.objects.ReceivableObjectSpec

class PatronObjectReceiver(private val receivableObjectSpec: ReceivableObjectSpec) :
    ObjectReceiver {

    override fun newObject(name: String, block: ObjectBuilderBlock) {
        ObjectBuilder.withSpec(name)
            .apply(block)
            .build()
            .let(receivableObjectSpec::addObject)
    }

    override fun newObject(className: ClassName, block: ObjectBuilderBlock) =
        newObject(className.newClassName, block)
}
