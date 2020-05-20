package dev.patron.interfaces.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.classes.ClassBuilderBlock

interface ClassReceiver {
    fun newClass(name: String, block: ClassBuilderBlock)
    fun newClass(className: ClassName, block: ClassBuilderBlock)
}
