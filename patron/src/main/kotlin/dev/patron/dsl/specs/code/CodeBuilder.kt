package dev.patron.dsl.specs.code

import com.squareup.kotlinpoet.CodeBlock
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.specs.LITERAL_MARKER

open class CodeBuilder(protected val spec: PatronCodeSpec = PatronCodeSpec()) :
    Builder<PatronCodeSpec, CodeBlock> by PatronBuilder(spec) {

    operator fun String.unaryMinus() {
        add(statement = this)
    }

    operator fun Pair<String, List<Any>>.unaryMinus() {
        add(
            statement = this.first,
            params = *this.second.toTypedArray()
        )
    }

    operator fun String.invoke(block: CodeBuilderBlock) {
        codeBlock(
            statement = this,
            block = block
        )
    }

    operator fun Pair<String, List<Any>>.invoke(block: CodeBuilderBlock) {
        codeBlock(
            statement = this.first,
            block = block,
            params = *this.second.toTypedArray()
        )
    }

    operator fun String.not() {
        returnWith(valueStatement = this)
    }

    operator fun Pair<String, String>.not() {
        returnWith(
            parameterMarker = this.first,
            valueStatement = this.second
        )
    }

    fun add(statement: String, vararg params: Any) {
        spec.add(
            statement = statement,
            params = *params
        )
    }

    fun justReturn(blockName: String? = null) {
        add(statement = RETURN_KEYWORD at blockName)
    }

    fun returnWith(
        valueStatement: String,
        parameterMarker: String = LITERAL_MARKER,
        blockName: String? = null
    ) {
        val returnStatement = RETURN_KEYWORD at blockName
        add(
            statement = "$returnStatement $parameterMarker",
            params = *arrayOf(valueStatement)
        )
    }

    fun codeBlock(statement: String, block: CodeBuilderBlock, vararg params: Any) {
        spec.beginBlock(
            statement = statement,
            params = *params
        ).apply { block() }.endBlock()
    }

    private infix fun String.at(block: String?) = block?.let { "$this@$it" } ?: this
}
