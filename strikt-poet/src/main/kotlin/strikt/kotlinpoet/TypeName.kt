package strikt.kotlinpoet

import com.squareup.kotlinpoet.TypeName
import strikt.api.Assertion

fun Assertion.Builder<TypeName>.isNullable(): Assertion.Builder<TypeName> = assert("type is nullable") {
    if (it.isNullable) pass() else fail()
}