package examples.delegation

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.STRING_MARKER
import dev.patron.dsl.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.jetbrains.annotations.Nullable

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

                annotateWith(Nullable::class.asClassName())

                returning {
                    type = String::class.asClassName()
                    isNullable = true
                }

                parameters {
                    ("args" to String::class.asClassName()) {
                        isVarargs = true
                        annotateWith(ClassName("android.support.annotation", "StringRes"))
                    }
                }

                code {
                    -("val text = %S" to listOf("Hello World!"))
                    -("println(text)")
                }
            }
        }
    }.writeTo(System.out)
}
