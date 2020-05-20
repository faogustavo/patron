package dev.patron.builders.classes

import com.squareup.kotlinpoet.ClassName
import dev.patron.Block
import dev.patron.builders.file.newFile

typealias ClassBuilderBlock = Block<ClassBuilder>

fun newClass(className: ClassName, block: ClassBuilderBlock) =
    newFile(
        fileName = className.simpleName,
        packageName = className.packageName
    ) { classes { newClass(className.simpleName, block) } }
