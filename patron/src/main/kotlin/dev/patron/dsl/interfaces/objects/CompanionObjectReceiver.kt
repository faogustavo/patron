package dev.patron.dsl.interfaces.objects

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.objects.ObjectBuilderBlock

interface CompanionObjectReceiver {
    fun companionObject(name: String = "", block: ObjectBuilderBlock)
    fun companionObject(className: ClassName, block: ObjectBuilderBlock)
}
