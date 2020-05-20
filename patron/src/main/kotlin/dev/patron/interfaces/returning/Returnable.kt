package dev.patron.interfaces.returning

import com.squareup.kotlinpoet.TypeName

interface Returnable {

    fun withReturnType(typeName: TypeName)
}
