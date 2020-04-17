package dev.patron.ext

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName

infix fun ClassName.getMember(name: String) = MemberName(this, name)