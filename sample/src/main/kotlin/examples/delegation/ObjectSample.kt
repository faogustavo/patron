package examples.delegation

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.STRING_MARKER
import dev.patron.dsl.TYPE_MARKER
import dev.patron.dsl.builders.file.newFile
import dev.patron.dsl.builders.objects.ObjectBuilder
import dev.patron.modifiers.Visibility

fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        val Greeter = ClassName("com.hello.world", "Greeter")
        val BrazilianGreeter = ClassName("com.hello.world", "Greeter.BrazilianGreeter")
        val HelloThere = ClassName("com.hello.world", "HelloThere")

        objects {
            Greeter {
                functions {
                    "greet" {
                        code {
                            -("println($STRING_MARKER)" to listOf("Hello World!"))
                        }
                    }
                }

                "BrazilianGreeter".invoke(::brazilianGreeter)
            }
        }

        newClass(HelloThere) {
            companionObject("RandomCompanion", block = ::brazilianGreeter)
        }

        functions {
            "main" {
                code {
                    -("$TYPE_MARKER.greet()" to listOf(Greeter))
                    -("$TYPE_MARKER.greet()" to listOf(BrazilianGreeter))
                }
            }
        }
    }.writeTo(System.out)
}

private fun brazilianGreeter(builder: ObjectBuilder) = with(builder) {
    visibility = Visibility.INTERNAL

    annotateWith(JvmStatic::class.asClassName())

    functions {
        "greet" {
            code {
                -("println($STRING_MARKER)" to listOf("Olá Mundo!"))
            }
        }
    }
}
