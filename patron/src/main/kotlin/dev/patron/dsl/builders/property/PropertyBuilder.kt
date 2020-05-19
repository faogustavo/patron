package dev.patron.dsl.builders.property

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.PropertySpec
import dev.patron.dsl.LITERAL_MARKER
import dev.patron.dsl.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.dsl.defaults.building.PatronBuilder
import dev.patron.dsl.defaults.visibility.PatronVisibilityChanger
import dev.patron.dsl.delegates.Pass
import dev.patron.dsl.interfaces.annotation.AnnotationDeclarator
import dev.patron.dsl.interfaces.building.Builder
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.dsl.specs.PatronPropertySpec

class PropertyBuilder(
    private val spec: PatronPropertySpec,
    private val name: String
) : Builder<PatronPropertySpec, PropertySpec> by PatronBuilder(spec),
    VisibilityChanger by PatronVisibilityChanger(spec),
    AnnotationDeclarator by PatronAnnotationDeclarator(spec) {

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

    fun initAtPrimaryConstructor() {
        spec.initWith(LITERAL_MARKER, name)
    }

    companion object {
        fun withSpec(
            name: String,
            className: ClassName,
            scope: PatronPropertySpec.Scope
        ) = PropertyBuilder(
            spec = PatronPropertySpec(name = name, type = className, scope = scope),
            name = name
        )
    }
}
