package dev.patron.builders.function

import dev.patron.Block
import dev.patron.builders.file.newFile

typealias FunctionBuilderBlock = Block<FunctionBuilder>
typealias FunctionReturnBlock = Block<FunctionBuilder.Return>

fun newFunction(
    fileName: String,
    packageName: String,
    functionName: String,
    block: FunctionBuilderBlock
) = newFile(
    fileName = fileName,
    packageName = packageName
) { functions { newFunction(functionName, block) } }
