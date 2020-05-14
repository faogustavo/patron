package dev.patron.dsl.defaults.returning

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.interfaces.returning.Returnable
import dev.patron.dsl.interfaces.returning.Returner

class PatronReturner(private val returnable: Returnable) : Returner {

    override var nullableReturn: Boolean = false
        set(value) {
            field = value
            setReturnType()
        }

    override var returnType: ClassName = Unit::class.asClassName()
        set(value) {
            field = value
            setReturnType()
        }

    private fun setReturnType() {
        returnable.withReturnType(returnType.copy(nullable = nullableReturn))
    }
}
