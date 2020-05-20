package dev.patron.interfaces.building

interface Builder<T : Buildable<O>, O> {
    fun build(): O
}
