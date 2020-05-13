package dev.patron.annotation

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import dev.patron.Builder

open class AnnotationBuilder(
    className: ClassName,
    site: AnnotationSpec.UseSiteTarget? = null
) : Builder<AnnotationSpec>() {
    protected val spec = AnnotationSpec.builder(className).apply {
        site?.let(::useSiteTarget)
    }

    infix fun String.withValue(value: Boolean) {
        spec.addMember(this.replaceFirst("%B", value.toString()))
    }

    infix fun String.withValue(value: Any) {
        spec.addMember(this, value)
    }

    override fun build() = spec.build()
}
