package dev.patron.dsl.delegates

import com.squareup.kotlinpoet.KModifier
import dev.patron.modifiers.Visibility
import kotlin.reflect.KProperty

private val visibilityModifiers = Visibility.values().map { it.modifier }

class VisibilityHandler(private val modifiers: MutableCollection<KModifier>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Visibility {
        val currentModifier = modifiers.find { it in visibilityModifiers }
        return Visibility.values().find { it.modifier == currentModifier } ?: Visibility.PUBLIC
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Visibility) {
        modifiers.removeAll(Visibility.values().map { it.modifier })
        modifiers.add(value.modifier)
    }
}
