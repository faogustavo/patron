package dev.patron.functions

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeName

class SetterBuilder(
    private val type: TypeName
) : BaseFunctionBuilder(FunSpec.setterBuilder()) {

    var name: String = "field"

    override fun build(): FunSpec {
        spec.addParameter(name, type)
        return super.build()
    }
}