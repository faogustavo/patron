package dev.patron.dsl.specs.file

import dev.patron.dsl.specs.Block

typealias FileBuilderBlock = Block<FileBuilder>

fun newFile(
    fileName: String,
    packageName: String = "",
    block: FileBuilderBlock
) = FileBuilder.withSpec(fileName, packageName)
    .apply(block)
    .build()
