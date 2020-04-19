package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import dev.patron.Builder
import dev.patron.modifiers.Visibility
import dev.patron.statement.StatementBuilder
import java.io.File

abstract class BaseFunctionBuilder(
    protected val spec: FunSpec.Builder
) : Builder<FunSpec>() {

    var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            spec.addModifiers(visibility.modifier)
        }

    fun statements(block: StatementBuilder.() -> Unit) {
        StatementBuilder(spec = spec).apply(block)
    }

    fun codeFrom(code: String) {
        spec.addCode(code)
    }

    fun codeFrom(file: File) {
        spec.addCode(file.readText())
    }

    override fun build() = spec.build()
}
