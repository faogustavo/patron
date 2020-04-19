package dev.patron

abstract class Builder<T> {
    internal abstract fun build(): T
}
