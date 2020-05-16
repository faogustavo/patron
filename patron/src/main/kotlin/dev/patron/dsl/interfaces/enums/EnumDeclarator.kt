package dev.patron.dsl.interfaces.enums

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.Block
import dev.patron.dsl.builders.enums.EnumBuilderBlock
import dev.patron.dsl.defaults.enums.PatronEnumReceiver

typealias EnumDeclaratorBlock = Block<EnumDeclarator.Block>

interface EnumDeclarator {
    fun enums(block: EnumDeclaratorBlock)
    class Block(spec: ReceivableEnumSpec) : EnumReceiver by PatronEnumReceiver(spec) {
        operator fun String.invoke(block: EnumBuilderBlock) {
            newEnum(
                name = this,
                block = block
            )
        }

        operator fun ClassName.invoke(block: EnumBuilderBlock) {
            newEnum(
                className = this,
                block = block
            )
        }
    }
}
