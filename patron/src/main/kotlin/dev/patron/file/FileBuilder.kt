package dev.patron.file

import com.squareup.kotlinpoet.FileSpec
import dev.patron.Builder
import dev.patron.classes.ClassBuilder
import dev.patron.enum.EnumBuilder
import dev.patron.functions.LocalFunctionBuilder
import dev.patron.properties.LocalPropertyBuilder

fun newFile(
    fileName: String,
    packageName: String = "",
    block: FileBuilder.() -> Unit
) = FileBuilder(
    spec = FileSpec.builder(
        packageName = packageName,
        fileName = fileName
    )
).apply(block).build()

class FileBuilder(protected val spec: FileSpec.Builder) : Builder<FileSpec>() {

    fun newClass(
        name: String,
        block: ClassBuilder.() -> Unit
    ) = ClassBuilder(name)
        .apply(block)
        .build()
        .also { spec.addType(it) }

    fun newFunction(
        name: String,
        block: LocalFunctionBuilder.() -> Unit
    ) = LocalFunctionBuilder(name)
        .apply(block)
        .build()
        .also { spec.addFunction(it) }

    fun properties(
        block: LocalPropertyBuilder.() -> Unit
    ) = LocalPropertyBuilder(spec).apply(block)

    fun enum(
        name: String,
        block: EnumBuilder.() -> Unit
    ) = EnumBuilder(name)
        .apply(block)
        .build()
        .also { spec.addType(it) }

    override fun build() = spec.build()
}
