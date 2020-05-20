package dev.patron.defaults.parameter

import dev.patron.interfaces.parameter.ParameterDeclarator
import dev.patron.interfaces.parameter.ParameterDeclaratorBlock
import dev.patron.interfaces.parameter.ReceivableParameter

class PatronParameterDeclarator(private val spec: ReceivableParameter) :
    ParameterDeclarator {

    override fun parameters(block: ParameterDeclaratorBlock) {
        ParameterDeclarator.Block(spec).apply(block)
    }
}
