package dev.patron.dsl.defaults.building

import dev.patron.dsl.interfaces.building.Buildable
import dev.patron.dsl.interfaces.building.Builder

class PatronBuilder<T : Buildable<O>, O>(private val buildable: T) : Builder<T, O> {
    override fun build() = buildable.build()
}
