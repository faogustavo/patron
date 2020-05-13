package dev.patron.dsl.interfaces.building

interface Builder<T : Buildable<O>, O> {
    fun build(): O
}
