package dev.patron.dsl.interfaces.returning

import com.squareup.kotlinpoet.ClassName

interface Returner {

    var returnType: ClassName
    var nullableReturn: Boolean
}
