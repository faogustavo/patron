package dev.patron.statement

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass

private const val RETURN_KEYWORD = "return"
private const val STRING_MARKER = "%S"
private const val STRING_TEMPLATE_MARKER = "%S"

class StatementBuilder(protected val spec: FunSpec.Builder) {

    operator fun String.unaryPlus() {
        add(this)
    }

    fun add(statement: String) {
        spec.addStatement(statement)
    }

    fun add(statement: String, type: KClass<*>) {
        spec.addStatement(statement, type.asClassName())
    }

    fun add(statement: String, type: ClassName) {
        spec.addStatement(statement, type)
    }

    fun add(statement: String, member: MemberName) {
        spec.addStatement(statement, member)
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

    fun returnWithString(valueStatement: String, blockName: String? = null) {
        val returnStatement = RETURN_KEYWORD withBlockName blockName
        spec.addStatement("$returnStatement $STRING_MARKER", valueStatement)
    }

    fun returnWithStringTemplate(valueStatement: String, blockName: String? = null) {
        val returnStatement = RETURN_KEYWORD withBlockName blockName
        spec.addStatement("$returnStatement $STRING_TEMPLATE_MARKER", valueStatement)
    }

    fun unitReturn(blockName: String? = null) {
        spec.addStatement(RETURN_KEYWORD withBlockName blockName)
    }

    private infix fun String.withBlockName(block: String?) = block?.let { "$this@$it" } ?: this

    private infix fun String.concatWithSpace(text: String) = "$this $text"

}