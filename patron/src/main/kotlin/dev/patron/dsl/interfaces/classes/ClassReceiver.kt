package dev.patron.dsl.interfaces.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.classes.ClassBuilderBlock

interface ClassReceiver {
    fun newClass(name: String, block: ClassBuilderBlock)
    fun newClass(className: ClassName, block: ClassBuilderBlock)
}
