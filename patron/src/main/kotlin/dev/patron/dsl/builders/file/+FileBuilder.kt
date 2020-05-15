package dev.patron.dsl.builders.file

import dev.patron.dsl.Block

typealias FileBuilderBlock = Block<FileBuilder>

fun newFile(
    fileName: String,
    packageName: String = "",
    block: FileBuilderBlock
) = FileBuilder.withSpec(fileName, packageName)
    .apply(block)
    .build()
