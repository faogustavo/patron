package dev.patron.dsl.defaults.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.classes.ClassBuilder
import dev.patron.dsl.builders.classes.ClassBuilderBlock
import dev.patron.dsl.interfaces.classes.ClassReceiver
import dev.patron.dsl.interfaces.classes.ReceivableClass
import dev.patron.ext.newClassName

class PatronClassReceiver(private val receivableClass: ReceivableClass) : ClassReceiver {

    override fun newClass(name: String, block: ClassBuilderBlock) {
        ClassBuilder.withSpec(name)
            .apply(block)
            .build()
            .let(receivableClass::addClass)
    }

    override fun newClass(className: ClassName, block: ClassBuilderBlock) =
        newClass(
            name = className.newClassName,
            block = block
        )
}
