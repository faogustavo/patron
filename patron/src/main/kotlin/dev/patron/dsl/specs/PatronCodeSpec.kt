package dev.patron.dsl.specs

import com.squareup.kotlinpoet.CodeBlock
import dev.patron.dsl.interfaces.building.Buildable

class PatronCodeSpec : Buildable<CodeBlock> {
    private val specBuilder: CodeBlock.Builder = CodeBlock.builder()

    fun add(statement: String, vararg params: Any): PatronCodeSpec {
        specBuilder.addStatement(statement, *params)
        return this
    }

    fun beginBlock(statement: String, vararg params: Any): PatronCodeSpec {
        specBuilder.beginControlFlow(statement, *params)
        return this
    }

    fun endBlock(): PatronCodeSpec {
        specBuilder.endControlFlow()
        return this
    }

    override fun build() = specBuilder.build()
}
