package dev.patron.dsl.builders.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.Block
import dev.patron.dsl.builders.file.newFile

typealias ClassBuilderBlock = Block<ClassBuilder>

fun newClass(className: ClassName, block: ClassBuilderBlock) = newFile(
    fileName = className.simpleName,
    packageName = className.packageName
) { newClass(className.simpleName, block) }
