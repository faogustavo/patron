package dev.patron.snapshots

import com.squareup.kotlinpoet.asClassName
import dev.patron.STRING_TEMPLATE_MARKER
import dev.patron.SnapshotTester
import dev.patron.builders.function.newFunction
import org.junit.Test

class FunctionTest : SnapshotTester() {

    @Test
    fun simpleFunction_shouldBuildCorrectly() = test {
        newFunction("greeter", "br.com.hello.world", "greeter") {
            parameters {
                -("name" to String::class.asClassName())
            }
            code {
                -("println($STRING_TEMPLATE_MARKER)" to listOf("Hello \$name"))
            }
        }.toString()
    }

    @Test
    fun extensionFunction_shouldBuildCorrectly() = test {
        newFunction("greet", "br.com.hello.world", "greeter") {
            extend(String::class.asClassName())
            code {
                -("println($STRING_TEMPLATE_MARKER)" to listOf("Hello \$this"))
            }
        }.toString()
    }
}
