package dev.patron.builders.parameter

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterSpec
import dev.patron.LITERAL_MARKER
import dev.patron.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.defaults.building.PatronBuilder
import dev.patron.delegates.Pass
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.building.Builder
import dev.patron.specs.PatronParameterSpec

class ParameterBuilder(private val spec: PatronParameterSpec) :
    Builder<PatronParameterSpec, ParameterSpec> by PatronBuilder(
        spec
    ),
    AnnotationDeclarator by PatronAnnotationDeclarator(
        spec
    ) {

    var isNullable: Boolean by Pass(
        spec,
        PatronParameterSpec::isNullable
    )
    var isVarargs: Boolean by Pass(
        spec,
        PatronParameterSpec::isVarargs
    )
    var defaultValue: Any?
        get() = spec.defaultValueArguments.firstOrNull()
        set(value) {
            spec.initWith(value)
        }

    fun initWithNull() {
        spec.initWith(LITERAL_MARKER, "null")
    }

    fun initWithFormat(format: String, vararg arguments: Any) {
        spec.initWith(format, *arguments)
    }

    companion object {
        fun withSpec(name: String, className: ClassName) =
            ParameterBuilder(
                PatronParameterSpec(
                    name,
                    className
                )
            )
    }
}
