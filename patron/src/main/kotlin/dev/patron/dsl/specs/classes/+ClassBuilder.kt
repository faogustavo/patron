package dev.patron.dsl.specs.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.dsl.specs.Block
import dev.patron.dsl.specs.file.newFile

typealias ClassBuilderBlock = Block<ClassBuilder>

fun newClass(className: ClassName, block: ClassBuilderBlock) = newFile(
    fileName = className.simpleName,
    packageName = className.packageName
) { newClass(className.simpleName, block) }
