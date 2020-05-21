package dev.patron.modifiers

import com.squareup.kotlinpoet.AnnotationSpec

enum class AnnotationSite(internal val specSite: AnnotationSpec.UseSiteTarget?) {
    DEFAULT(null),
    FILE(AnnotationSpec.UseSiteTarget.FILE)
}
