package dev.patron.dsl.builders.property

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.PropertySpec
import dev.patron.dsl.defaults.annotation.PatronAnnotator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.delegates.Pass
import dev.patron.dsl.interfaces.annotation.Annotator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronPropertySpec

class PropertyBuilder(private val spec: PatronPropertySpec) :
    Builder<PatronPropertySpec, PropertySpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    Annotator by PatronAnnotator(spec) {

    var isLateInit: Boolean by Pass(spec, PatronPropertySpec::isLateInit)
    var isMutable: Boolean by Pass(spec, PatronPropertySpec::isMutable)
    var isNullable: Boolean by Pass(spec, PatronPropertySpec::isNullable)
    var isConst: Boolean by Pass(spec, PatronPropertySpec::isConst)
    var initWith: Any?
        get() = spec.initArguments.firstOrNull()
        set(value) {
            spec.initWith(value)
        }

    fun initWithFormat(format: String, vararg arguments: Any) {
        spec.initWith(format, *arguments)
    }

    companion object {
        fun withSpec(name: String, className: ClassName) = PropertyBuilder(PatronPropertySpec(name, className))
    }
}
