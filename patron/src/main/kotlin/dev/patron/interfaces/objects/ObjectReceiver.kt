package dev.patron.interfaces.objects

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.objects.ObjectBuilderBlock

interface ObjectReceiver {
    fun newObject(name: String, block: ObjectBuilderBlock)
    fun newObject(className: ClassName, block: ObjectBuilderBlock)
}
