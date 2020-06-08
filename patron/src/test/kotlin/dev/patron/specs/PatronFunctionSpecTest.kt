package dev.patron.specs

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import dev.patron.modifiers.FunctionType
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PatronFunctionSpecTest {

    private val builder = mockk<FunSpec.Builder>()
    private val modifiers = mutableListOf<KModifier>()

    @Before
    fun setUp() {
        mockkObject(FunSpec)

        every { FunSpec.builder(any()) } returns builder
        every { FunSpec.constructorBuilder() } returns builder
        every { FunSpec.getterBuilder() } returns builder
        every { FunSpec.setterBuilder() } returns builder

        every { builder.addAnnotation(any<AnnotationSpec>()) } returns builder
        every { builder.returns(any<TypeName>()) } returns builder
        every { builder.addCode(any<CodeBlock>()) } returns builder
        every { builder.addParameter(any()) } returns builder
        every { builder.receiver(any<ClassName>()) } returns builder

        every { builder.modifiers } returns modifiers
        every { builder.build() } returns mockk()
    }

    @Test
    fun build_shouldCallBuilderMethod() {
        testSpec().build()

        verify(exactly = 1) { builder.build() }
    }

    @Test
    fun init_withDefaultType_returnsDefaultBuilder() {
        testSpec(FunctionType.DEFAULT)

        verify(exactly = 1) { FunSpec.builder(any()) }
        verify(exactly = 0) { FunSpec.constructorBuilder() }
        verify(exactly = 0) { FunSpec.getterBuilder() }
        verify(exactly = 0) { FunSpec.setterBuilder() }
    }

    @Test
    fun init_withConstructorType_returnsConstructorBuilder() {
        testSpec(FunctionType.CONSTRUCTOR)

        verify(exactly = 1) { FunSpec.constructorBuilder() }
        verify(exactly = 0) { FunSpec.builder(any()) }
        verify(exactly = 0) { FunSpec.getterBuilder() }
        verify(exactly = 0) { FunSpec.setterBuilder() }
    }

    @Test
    fun init_withGetterType_returnsGetterBuilder() {
        testSpec(FunctionType.GETTER)

        verify(exactly = 1) { FunSpec.getterBuilder() }
        verify(exactly = 0) { FunSpec.builder(any()) }
        verify(exactly = 0) { FunSpec.constructorBuilder() }
        verify(exactly = 0) { FunSpec.setterBuilder() }
    }

    @Test
    fun init_withSetterType_returnsSetterBuilder() {
        testSpec(FunctionType.SETTER)

        verify(exactly = 1) { FunSpec.setterBuilder() }
        verify(exactly = 0) { FunSpec.builder(any()) }
        verify(exactly = 0) { FunSpec.constructorBuilder() }
        verify(exactly = 0) { FunSpec.getterBuilder() }
    }

    @Test
    fun annotateWith_addAnnotation() {
        val annotation = mockk<AnnotationSpec>()

        testSpec().annotateWith(annotation)

        verify(exactly = 1) { builder.addAnnotation(annotation) }
    }

    @Test
    fun withReturnType_callReturns() {
        val type = mockk<TypeName>()

        testSpec().withReturnType(type)

        verify(exactly = 1) { builder.returns(type) }
    }

    @Test
    fun addParameter_addParamter() {
        val parameter = mockk<ParameterSpec>()

        testSpec().addParameter(parameter)

        verify(exactly = 1) { builder.addParameter(parameter) }
    }

    @Test
    fun addCode_addCode() {
        val code = mockk<CodeBlock>()

        testSpec().addCode(code)

        verify(exactly = 1) { builder.addCode(code) }
    }

    @Test
    fun extend_addReceiver() {
        val className = mockk<ClassName>()

        testSpec().extend(className)

        verify(exactly = 1) { builder.receiver(className) }
    }

    private fun testSpec() = PatronFunctionSpec("main")

    private fun testSpec(type: FunctionType) = PatronFunctionSpec("main", type)
}
