package dev.patron.statement

import com.squareup.kotlinpoet.FunSpec

private const val RETURN_KEYWORD = "return"

class StatementBuilder(protected val spec: FunSpec.Builder) {

    operator fun String.unaryPlus() {
        add(this)
    }

    fun add(statement: String) {
        spec.addStatement(statement)
    }

    fun controlFlow(statement: String, block: StatementBuilder.() -> Unit) {
        spec.beginControlFlow(statement)
        block()
        spec.endControlFlow()
    }

    fun returnWith(valueStatement: String, blockName: String? = null) {
        val returnStatement = RETURN_KEYWORD withBlockName blockName
        spec.addStatement(returnStatement concatWithSpace valueStatement)
    }

    fun unitReturn(blockName: String? = null) {
        spec.addStatement(RETURN_KEYWORD withBlockName blockName)
    }

    private infix fun String.withBlockName(block: String?) = block?.let { "$this@$it" } ?: this

    private infix fun String.concatWithSpace(text: String) = "$this $text"

}