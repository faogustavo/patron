package dev.patron

import com.squareup.kotlinpoet.FileSpec

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
        .run(spec::addType)

    override fun build() = spec.build()
}