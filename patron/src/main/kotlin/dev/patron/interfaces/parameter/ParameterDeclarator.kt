package dev.patron.interfaces.parameter

import com.squareup.kotlinpoet.ClassName
import dev.patron.Block
import dev.patron.builders.parameter.ParameterBuilderBlock
import dev.patron.defaults.parameter.PatronParameterReceiver

typealias ParameterDeclaratorBlock = Block<ParameterDeclarator.Block>

interface ParameterDeclarator {
    fun parameters(block: ParameterDeclaratorBlock)
    class Block(spec: ReceivableParameter) : ParameterReceiver by PatronParameterReceiver(
        spec
    ) {

        operator fun Pair<String, ClassName>.unaryMinus() {
            newParameter(
                name = first,
                type = second,
                block = {}
            )
        }

        operator fun Pair<String, ClassName>.invoke(block: ParameterBuilderBlock) {
            newParameter(
                name = first,
                type = second,
                block = block
            )
        }
    }
}
