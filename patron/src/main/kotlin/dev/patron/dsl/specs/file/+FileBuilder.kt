package dev.patron.dsl.specs.file

typealias FileBuilderBlock = FileBuilder.() -> Unit

fun newFile(
    fileName: String,
    packageName: String = "",
    block: FileBuilderBlock
) = FileBuilder.withSpec(fileName, packageName)
    .apply(block)
    .build()
