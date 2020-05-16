package dev.patron.dsl.delegates

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty

class Pass<T, R>(
    private val other: T,
    private val property: KMutableProperty1<T, R>
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): R {
        return this.property.get(other)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: R) {
        this.property.set(other, value)
    }
}
