package dev.patron.builders.property

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.PropertySpec
import dev.patron.LITERAL_MARKER
import dev.patron.builders.function.FunctionBuilder
import dev.patron.builders.function.FunctionBuilderBlock
import dev.patron.defaults.annotation.PatronAnnotationDeclarator
import dev.patron.defaults.building.PatronBuilder
import dev.patron.defaults.visibility.PatronVisibilityChanger
import dev.patron.delegates.Pass
import dev.patron.interfaces.annotation.AnnotationDeclarator
import dev.patron.interfaces.building.Builder
import dev.patron.interfaces.visibility.VisibilityChanger
import dev.patron.specs.PatronPropertySpec

class PropertyBuilder(
    private val spec: PatronPropertySpec,
    private val name: String
) : Builder<PatronPropertySpec, PropertySpec> by PatronBuilder(
    spec
),
    VisibilityChanger by PatronVisibilityChanger(
        spec
    ),
    AnnotationDeclarator by PatronAnnotationDeclarator(
        spec
    ) {

    var isLateInit: Boolean by Pass(
        spec,
        PatronPropertySpec::isLateInit
    )
    var isMutable: Boolean by Pass(
        spec,
        PatronPropertySpec::isMutable
    )
    var isNullable: Boolean by Pass(
        spec,
        PatronPropertySpec::isNullable
    )
    var isConst: Boolean by Pass(
        spec,
        PatronPropertySpec::isConst
    )
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

    fun getter(block: FunctionBuilderBlock) {
        FunctionBuilder.withGetterSpec()
            .apply(block)
            .build()
            .let { spec.getter = it }
    }

    fun setter(block: FunctionBuilderBlock) {
        FunctionBuilder.withSetterSpec()
            .apply(block)
            .build()
            .let { spec.setter = it }
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
