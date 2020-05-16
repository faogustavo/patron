package dev.patron.dsl.interfaces.returning

import com.squareup.kotlinpoet.ClassName

interface Returner {

    var type: ClassName
    var isNullable: Boolean
}
