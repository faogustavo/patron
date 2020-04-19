package dev.patron.properties

import com.squareup.kotlinpoet.FileSpec

class LocalPropertyBuilder(protected val fileSpec: FileSpec.Builder) : PropertyBuilder() {
    override fun addProperty(builder: PropertyItemBuilder) {
        fileSpec.addProperty(builder.build())
    }
}
