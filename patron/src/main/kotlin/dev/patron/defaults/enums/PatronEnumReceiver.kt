package dev.patron.defaults.enums

import com.squareup.kotlinpoet.ClassName
import dev.patron.builders.enums.EnumBuilder
import dev.patron.builders.enums.EnumBuilderBlock
import dev.patron.ext.newClassName
import dev.patron.interfaces.enums.EnumReceiver
import dev.patron.interfaces.enums.ReceivableEnumSpec

class PatronEnumReceiver(private val receivableEnumSpec: ReceivableEnumSpec) :
    EnumReceiver {

    override fun newEnum(name: String, block: EnumBuilderBlock) {
        EnumBuilder.withSpec(name)
            .apply(block)
            .build()
            .let(receivableEnumSpec::addEnum)
    }

    override fun newEnum(className: ClassName, block: EnumBuilderBlock) =
        newEnum(
            name = className.newClassName,
            block = block
        )
}
