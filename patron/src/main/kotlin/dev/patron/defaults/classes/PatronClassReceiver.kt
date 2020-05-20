package dev.patron.defaults.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.classes.ClassBuilder
import dev.patron.builders.classes.ClassBuilderBlock
import dev.patron.ext.newClassName
import dev.patron.interfaces.classes.ClassReceiver
import dev.patron.interfaces.classes.ReceivableClassSpec

class PatronClassReceiver(private val receivableClassSpec: ReceivableClassSpec) :
    ClassReceiver {

    override fun newClass(name: String, block: ClassBuilderBlock) {
        ClassBuilder.withSpec(name)
            .apply(block)
            .build()
            .let(receivableClassSpec::addClass)
    }

    override fun newClass(className: ClassName, block: ClassBuilderBlock) =
        newClass(
            name = className.newClassName,
            block = block
        )
}
