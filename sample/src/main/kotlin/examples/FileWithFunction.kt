package examples

import com.squareup.kotlinpoet.asClassName
import dev.patron.STRING_MARKER
import dev.patron.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.jetbrains.annotations.Nullable

// Example from the new DSL
fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        annotations {
            (JvmName::class.asClassName()) {
                "name = $STRING_MARKER" withValue "PatronFunctions"
            }
        }

        functions {
            "main" {
                visibility = Visibility.PRIVATE

                returning {
                    type = String::class.asClassName()
                    isNullable = true
                }

                annotations {
                    -Nullable::class.asClassName()
                }

                code {
                    -("val text = %S" to listOf("Hello World!"))
                    -("println(text)")
                }
            }
        }
    }.writeTo(System.out)
}
