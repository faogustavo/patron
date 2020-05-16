package dev.patron.dsl.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import dev.patron.dsl.LITERAL_MARKER
import dev.patron.dsl.STRING_MARKER
import dev.patron.dsl.delegates.VisibilityHandler
import dev.patron.dsl.interfaces.annotation.AnnotableSpec
import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.Visibility

class PatronPropertySpec(
    private val name: String,
    private val type: ClassName
) : Buildable<PropertySpec>, ChangeableVisibility, AnnotableSpec {

    private val specAnnotations = mutableListOf<AnnotationSpec>()
    private val specModifiers = mutableListOf<KModifier>()
    private val builtType: TypeName
        get() = type.copy(nullable = isNullable)

    private var initFormat: String = if (type == STRING) STRING_MARKER else LITERAL_MARKER
    internal var initArguments: Array<out Any?> = emptyArray()

    override var visibility: Visibility by VisibilityHandler(specModifiers)

    var isNullable: Boolean = false
    var isLateInit: Boolean = false
        set(value) {
            field = value
            if (value) {
                isMutable = true
                specModifiers.add(KModifier.LATEINIT)
            } else {
                specModifiers.remove(KModifier.LATEINIT)
            }
        }

    var isMutable: Boolean = false
        set(value) {
            field = value
            if (!value) {
                isLateInit = false
            }
        }

    var isConst: Boolean = false
        set(value) {
            field = value
            if (value) {
                specModifiers.add(KModifier.CONST)
            } else {
                specModifiers.remove(KModifier.CONST)
            }
        }

    fun initWith(value: Any?) {
        initArguments = arrayOf(value)
    }

    fun initWith(format: String = initFormat, vararg arguments: Any?) {
        initFormat = format
        initArguments = arguments
    }

    override fun annotateWith(annotationSpec: AnnotationSpec) {
        specAnnotations.add(annotationSpec)
    }

    private fun checkPropertyConstraints() {
        if (isLateInit) {
            check(initArguments.isEmpty()) {
                "Late init vars shouldn't have initial values"
            }

            check(isMutable) {
                "Late init properties shouldn't not be mutable"
            }
        }

        if (!isMutable) {
            check(isNullable || initArguments.isNotEmpty()) {
                "Immutable properties should have an initial value"
            }
        }
    }

    override fun build() = PropertySpec.builder(name, builtType).apply {
        checkPropertyConstraints()

        mutable(isMutable)
        addModifiers(specModifiers)
        addAnnotations(specAnnotations)

        val initFormat = initFormat
        val initArgs = initArguments

        when {
            initArgs.isNotEmpty() -> initializer(initFormat, *initArgs)
            isNullable && !isLateInit -> initializer("null")
        }
    }.build()
}
