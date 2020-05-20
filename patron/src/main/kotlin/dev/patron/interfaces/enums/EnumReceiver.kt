package dev.patron.interfaces.enums

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.enums.EnumBuilderBlock

interface EnumReceiver {
    fun newEnum(name: String, block: EnumBuilderBlock)
    fun newEnum(className: ClassName, block: EnumBuilderBlock)
}
