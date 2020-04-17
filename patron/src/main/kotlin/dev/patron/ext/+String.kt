package dev.patron.ext

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName

private fun String.splitPackage(): Pair<String, String> {
    val packages = this.split(".")

    val name = packages.last()
    val pkg = packages.dropLast(1).joinToString(".")

    return pkg to name
}

fun String.asClassName() = splitPackage().let { ClassName(it.first, it.second) }

fun String.asMemberName() = splitPackage().let { MemberName(it.first, it.second) }