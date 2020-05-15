package examples.delegation

import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.STRING_MARKER
import dev.patron.dsl.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.jetbrains.annotations.Nullable

// Example from the new DSL
fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        annotateWith(JvmName::class.asClassName()) {
            "name = $STRING_MARKER" withValue "PatronFunctions"
        }

        newFunction("main") {
            visibility = Visibility.PRIVATE

            returnType = String::class.asClassName()
            nullableReturn = true

            annotateWith(Nullable::class.asClassName())

            code {
                -("val text = %S" to listOf("Hello World!"))
                -("println(text)")
            }
        }
    }.writeTo(System.out)
}
