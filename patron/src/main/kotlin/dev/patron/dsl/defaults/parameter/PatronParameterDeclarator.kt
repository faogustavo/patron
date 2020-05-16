package dev.patron.dsl.defaults.parameter

import dev.patron.dsl.interfaces.parameter.ParameterDeclarator
import dev.patron.dsl.interfaces.parameter.ParameterDeclaratorBlock
import dev.patron.dsl.interfaces.parameter.ReceivableParameter

class PatronParameterDeclarator(private val spec: ReceivableParameter) : ParameterDeclarator {

    override fun parameters(block: ParameterDeclaratorBlock) {
        ParameterDeclarator.Block(spec).apply(block)
    }
}
