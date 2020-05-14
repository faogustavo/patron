package dev.patron.dsl.specs

typealias Block<T> = T.() -> Unit

const val STRING_MARKER = "%S"
const val STRING_TEMPLATE_MARKER = "%P"
const val TYPE_MARKER = "%T"
const val MEMBER_MARKER = "%M"
const val NAME_MARKER = "%N"
const val LITERAL_MARKER = "%L"
