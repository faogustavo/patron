package dev.patron.functions

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asTypeName
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.reflect.KClass

open class ReturnableFunctionBuilderTest : BaseFunctionBuilderTest() {

    private val returningSubject by lazy { object : ReturnableFunctionBuilder(spec) {} }
    override val subject
        get() = returningSubject

    @Before
    override fun setUp() {
        super.setUp()

        every { spec.returns(any<KClass<*>>()) } returns spec
        every { spec.returns(any<ClassName>()) } returns spec
    }

    @Test
    fun returning_withKClass_declaresReturnType() {
        returningSubject.returning(String::class)

        verify(exactly = 1) { spec.returns(String::class) }
    }

    @Test
    fun returning_withClassName_declaresReturnType() {
        val className = String::class.asTypeName()

        returningSubject.returning(className)

        verify(exactly = 1) { spec.returns(className) }
    }

}
