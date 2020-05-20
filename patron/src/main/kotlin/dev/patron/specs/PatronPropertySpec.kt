package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import dev.patron.LITERAL_MARKER
import dev.patron.STRING_MARKER
import dev.patron.delegates.VisibilityHandler
import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.building.Buildable
import dev.patron.interfaces.visibility.ChangeableVisibility
import dev.patron.modifiers.Visibility

class PatronPropertySpec(
    private val name: String,
    private val type: ClassName,
    private val scope: Scope
) : Buildable<PropertySpec>,
    ChangeableVisibility,
    AnnotableSpec {

    private val specAnnotations = mutableListOf<AnnotationSpec>()
    private val specModifiers = mutableListOf<KModifier>()
    private val builtType: TypeName
        get() = type.copy(nullable = isNullable)

    private var initFormat: String = if (type == STRING) STRING_MARKER else LITERAL_MARKER
    internal var initArguments: Array<out Any?> = emptyArray()

    override var visibility: Visibility by VisibilityHandler(specModifiers)

    internal var getter: FunSpec? = null
    internal var setter: FunSpec? = null

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

        if (!isMutable && scope != Scope.CLASS) {
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

        getter?.let(this::getter)
        setter?.let(this::setter)

        val initFormat = initFormat
        val initArgs = initArguments

        when {
            initArgs.isNotEmpty() -> initializer(initFormat, *initArgs)
            isNullable && !isLateInit && getter == null -> initializer("null")
        }
    }.build()

    enum class Scope {
        CLASS, FILE, ENUM, OBJECT
    }
}
