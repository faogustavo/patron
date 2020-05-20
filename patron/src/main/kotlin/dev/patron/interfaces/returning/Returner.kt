package dev.patron.interfaces.returning

import com.squareup.kotlinpoet.ClassName

interface Returner {

    var type: ClassName
    var isNullable: Boolean
}
