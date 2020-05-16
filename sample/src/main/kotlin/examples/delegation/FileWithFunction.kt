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

        functions {
            "main" {
                visibility = Visibility.PRIVATE

                returning {
                    type = String::class.asClassName()
                    isNullable = true
                }

                annotateWith(Nullable::class.asClassName())

                code {
                    -("val text = %S" to listOf("Hello World!"))
                    -("println(text)")
                }
            }
        }
    }.writeTo(System.out)
}
