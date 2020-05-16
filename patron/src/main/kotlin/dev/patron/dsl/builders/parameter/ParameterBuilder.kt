package dev.patron.dsl.builders.parameter

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterSpec
import dev.patron.dsl.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.delegates.Pass
import dev.patron.dsl.interfaces.annotation.AnnotationDeclarator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.specs.PatronParameterSpec

class ParameterBuilder(private val spec: PatronParameterSpec) :
    Builder<PatronParameterSpec, ParameterSpec> by PatronBuilder(spec),
    AnnotationDeclarator by PatronAnnotationDeclarator(spec) {

    var isNullable: Boolean by Pass(spec, PatronParameterSpec::isNullable)
    var isVarargs: Boolean by Pass(spec, PatronParameterSpec::isVarargs)
    var defaultValue: Any?
        get() = spec.defaultValueArguments.firstOrNull()
        set(value) {
            spec.initWith(value)
        }

    fun initWithFormat(format: String, vararg arguments: Any) {
        spec.initWith(format, *arguments)
    }

    companion object {
        fun withSpec(name: String, className: ClassName) = ParameterBuilder(PatronParameterSpec(name, className))
    }
}
