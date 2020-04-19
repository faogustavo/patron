package dev.patron.properties

import com.squareup.kotlinpoet.TypeSpec

class ClassPropertyBuilder(protected val typeSpec: TypeSpec.Builder) : PropertyBuilder() {
    override fun addProperty(builder: PropertyItemBuilder) {
        typeSpec.addProperty(builder.build())
    }
}
