package dev.patron.dsl.defaults.enums

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.builders.enums.EnumBuilder
import dev.patron.dsl.builders.enums.EnumBuilderBlock
import dev.patron.dsl.interfaces.enums.EnumReceiver
import dev.patron.dsl.interfaces.enums.ReceivableEnumSpec
import dev.patron.ext.newClassName

class PatronEnumReceiver(private val receivableEnumSpec: ReceivableEnumSpec) : EnumReceiver {

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
