package dev.patron.functions.testers

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asTypeName
import dev.patron.functions.ReturnableFunctionBuilder
import io.mockk.every
import io.mockk.verify
import kotlin.reflect.KClass
import org.junit.Before
import org.junit.Test

abstract class BaseReturnableFunctionTester<T : ReturnableFunctionBuilder> : BaseFunctionTester<T>() {

    @Before
    override fun setUp() {
        super.setUp()

        every { spec.returns(any<KClass<*>>()) } returns spec
        every { spec.returns(any<ClassName>()) } returns spec
    }

    @Test
    fun returning_withKClass_declaresReturnType() {
        subject.returning(String::class)

        verify(exactly = 1) { spec.returns(String::class) }
    }

    @Test
    fun returning_withClassName_declaresReturnType() {
        val className = String::class.asTypeName()

        subject.returning(className)

        verify(exactly = 1) { spec.returns(className) }
    }
}
