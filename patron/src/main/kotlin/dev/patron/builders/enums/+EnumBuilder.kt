package dev.patron.builders.enums

import com.squareup.kotlinpoet.ClassName
import dev.patron.Block
import dev.patron.builders.file.newFile

typealias EnumBuilderBlock = Block<EnumBuilder>
typealias SimpleEnumValueBlock = Block<EnumBuilder.ValueSetter>

fun newEnum(className: ClassName, block: EnumBuilderBlock) =
    newFile(
        fileName = className.simpleName,
        packageName = className.packageName
    ) { enums { newEnum(className.simpleName, block) } }
