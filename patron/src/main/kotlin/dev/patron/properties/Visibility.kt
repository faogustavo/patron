package dev.patron.properties

import com.squareup.kotlinpoet.KModifier

enum class Visibility(internal val modifier: KModifier) {
    PUBLIC(KModifier.PUBLIC),
    INTERNAL(KModifier.INTERNAL),
    PROTECTED(KModifier.PROTECTED),
    PRIVATE(KModifier.PRIVATE)
}
