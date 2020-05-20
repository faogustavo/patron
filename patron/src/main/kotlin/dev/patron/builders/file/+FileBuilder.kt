package dev.patron.builders.file

import dev.patron.Block

typealias FileBuilderBlock = Block<FileBuilder>

fun newFile(
    fileName: String,
    packageName: String = "",
    block: FileBuilderBlock
) = FileBuilder.withSpec(fileName, packageName)
    .apply(block)
    .build()
