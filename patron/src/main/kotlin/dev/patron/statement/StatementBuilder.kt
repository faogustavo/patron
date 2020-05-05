package dev.patron.statement

import com.squareup.kotlinpoet.FunSpec

class StatementBuilder(protected val spec: FunSpec.Builder) {

    companion object {
        private const val RETURN_KEYWORD = "return"
        private const val STRING_MARKER = "%S"
        private const val STRING_TEMPLATE_MARKER = "%P"
    }

    operator fun String.unaryPlus() {
        add(this)
    }

    fun add(statement: String, vararg params: Any) {
        spec.addStatement(statement, *params)
    }

    fun controlFlow(statement: String, block: StatementBuilder.() -> Unit) {
        spec.beginControlFlow(statement)
            .apply { this@StatementBuilder.block() }
            .endControlFlow()
    }

    fun returnWith(valueStatement: String, blockName: String? = null) {
        val returnStatement = RETURN_KEYWORD withBlockName blockName
        add("$returnStatement $valueStatement")
    }

    fun returnWithString(valueStatement: String, blockName: String? = null) {
        val returnStatement = RETURN_KEYWORD withBlockName blockName
        add("$returnStatement $STRING_MARKER", valueStatement)
    }

    fun returnWithStringTemplate(valueStatement: String, blockName: String? = null) {
        val returnStatement = RETURN_KEYWORD withBlockName blockName
        add("$returnStatement $STRING_TEMPLATE_MARKER", valueStatement)
    }

    fun unitReturn(blockName: String? = null) {
        add(RETURN_KEYWORD withBlockName blockName)
    }

    private infix fun String.withBlockName(block: String?) = block?.let { "$this@$it" } ?: this
}
