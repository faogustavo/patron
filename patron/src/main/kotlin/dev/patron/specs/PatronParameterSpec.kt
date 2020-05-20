package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import dev.patron.LITERAL_MARKER
import dev.patron.STRING_MARKER
import dev.patron.interfaces.annotation.AnnotableSpec
import dev.patron.interfaces.building.Buildable

class PatronParameterSpec(
    private val name: String,
    private val type: ClassName
) : Buildable<ParameterSpec>,
    AnnotableSpec {

    private val specModifiers = mutableListOf<KModifier>()
    private val specAnnotations = mutableListOf<AnnotationSpec>()
    private val builtType: TypeName
        get() = type.copy(nullable = isNullable)

    private var defaultValueFormat: String = if (type == STRING) STRING_MARKER else LITERAL_MARKER
    internal var defaultValueArguments: Array<out Any?> = emptyArray()

    var isNullable: Boolean = false
    var isVarargs: Boolean = false
        set(value) {
            field = value
            if (value) {
                specModifiers.add(KModifier.VARARG)
            } else {
                specModifiers.remove(KModifier.VARARG)
            }
        }

    fun initWith(value: Any?) {
        defaultValueArguments = arrayOf(value)
    }

    fun initWith(format: String = defaultValueFormat, vararg arguments: Any?) {
        defaultValueFormat = format
        defaultValueArguments = arguments
    }

    override fun annotateWith(annotationSpec: AnnotationSpec) {
        specAnnotations.add(annotationSpec)
    }

    override fun build() = ParameterSpec.builder(name, builtType).apply {
        addModifiers(specModifiers)
        addAnnotations(specAnnotations)
        if (defaultValueArguments.isNotEmpty()) {
            defaultValue(defaultValueFormat, *defaultValueArguments)
        }
    }.build()
}
