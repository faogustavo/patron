package dev.patron.properties

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import dev.patron.Builder
import dev.patron.functions.GetterBuilder
import dev.patron.functions.SetterBuilder
import dev.patron.modifiers.Visibility
import kotlin.reflect.KClass

class PropertyItemBuilder(
    protected val name: String,
    protected val type: ClassName
) : Builder<PropertySpec>() {

    constructor(
        name: String,
        type: KClass<*>
    ) : this(name, type.asTypeName())

    var isMutable: Boolean = false
    var visibility: Visibility = Visibility.PUBLIC
    var initWith: String? = null
    var isNullable: Boolean = false

    private val builtType: TypeName
        get() = type.copy(nullable = isNullable)

    private var getter: GetterBuilder? = null
    private var setter: SetterBuilder? = null

    fun getter(block: GetterBuilder.() -> Unit) {
        getter = GetterBuilder().apply(block)
    }

    fun setter(block: SetterBuilder.() -> Unit) {
        setter = SetterBuilder(builtType).apply(block)
    }

    override fun build() = PropertySpec.builder(
        name = name,
        type = builtType
    ).apply {
        mutable(isMutable)
        initWith?.let { initializer(it) }
        addModifiers(visibility.modifier)
        getter?.let { getter(it.build()) }
        setter?.let { setter(it.build()) }
    }.build()
}
