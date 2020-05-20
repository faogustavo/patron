package dev.patron.defaults.building

import dev.patron.interfaces.building.Buildable
import dev.patron.interfaces.building.Builder

class PatronBuilder<T : Buildable<O>, O>(private val buildable: T) :
    Builder<T, O> {
    override fun build() = buildable.build()
}
