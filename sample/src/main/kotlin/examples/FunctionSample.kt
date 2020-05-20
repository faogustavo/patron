package examples

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.STRING_MARKER
import dev.patron.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.jetbrains.annotations.Nullable

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

                annotations {
                    -Nullable::class.asClassName()
                }

                returning {
                    type = String::class.asClassName()
                    isNullable = true
                }

                parameters {
                    ("args" to String::class.asClassName()) {
                        isVarargs = true
                        annotations {
                            -ClassName("android.support.annotation", "StringRes")
                        }
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
