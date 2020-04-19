package strikt.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import strikt.api.Assertion

fun Assertion.Builder<Iterable<KModifier>>.hasModifier(modifier: KModifier, message: String? = null) =
    assert(message ?: "has modifier $modifier") {
        if (it.any { it == modifier }) pass() else fail()
    }

fun Assertion.Builder<Iterable<KModifier>>.isVarargs() = hasModifier(KModifier.VARARG, "has Varargs modifier")