package examples

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.STRING_MARKER
import dev.patron.TYPE_MARKER
import dev.patron.builders.file.newFile
import dev.patron.builders.objects.ObjectBuilder
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

        classes {
            HelloThere {
                companionObject("RandomCompanion", block = ::brazilianGreeter)
            }
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

    annotations {
        -JvmStatic::class.asClassName()
    }

    functions {
        "greet" {
            code {
                -("println($STRING_MARKER)" to listOf("Olá Mundo!"))
            }
        }
    }
}