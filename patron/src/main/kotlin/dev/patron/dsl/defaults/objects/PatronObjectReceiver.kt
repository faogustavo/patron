package dev.patron.dsl.defaults.objects

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.objects.ObjectBuilder
import dev.patron.dsl.builders.objects.ObjectBuilderBlock
import dev.patron.dsl.interfaces.objects.ObjectReceiver
import dev.patron.dsl.interfaces.objects.ReceivableObjectSpec
import dev.patron.ext.newClassName

class PatronObjectReceiver(private val receivableObjectSpec: ReceivableObjectSpec) : ObjectReceiver {

    override fun newObject(name: String, block: ObjectBuilderBlock) {
        ObjectBuilder.withSpec(name)
            .apply(block)
            .build()
            .let(receivableObjectSpec::addObject)
    }

    override fun newObject(className: ClassName, block: ObjectBuilderBlock) =
        newObject(className.newClassName, block)
}
