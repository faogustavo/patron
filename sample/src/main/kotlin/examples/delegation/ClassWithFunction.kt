package examples.delegation

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.STRING_MARKER
import dev.patron.dsl.TYPE_MARKER
import dev.patron.dsl.builders.file.newFile
import dev.patron.modifiers.Visibility

// Example from the new DSL
fun main() {
    val Greeter = ClassName("com.hello.world", "Greeter")
    val InnerGreeter = ClassName("com.hello.world", "Greeter.InnerGreeter")

    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        classes {
            Greeter {
                functions {
                    "greet" {
                        visibility = Visibility.INTERNAL

                        returning {
                            type = String::class.asClassName()
                        }

                        code {
                            !(STRING_MARKER to "Hello from greeter!")
                        }
                    }
                }

                classes {
                    InnerGreeter {
                        functions {
                            "innerGreet" {
                                visibility = Visibility.INTERNAL

                                returning {
                                    type = String::class.asClassName()
                                }

                                code {
                                    !(STRING_MARKER to "Hello from inner greeter!")
                                }
                            }
                        }
                    }
                }
            }
        }

        functions {
            "main" {
                code {
                    -("val greeter = $TYPE_MARKER()" to listOf(Greeter))
                    -"println(greeter.greet())"
                    -""
                    -("val innerGreeter = $TYPE_MARKER()" to listOf(InnerGreeter))
                    -"println(innerGreeter.innerGreet())"
                }
            }
        }
    }.writeTo(System.out)
}
