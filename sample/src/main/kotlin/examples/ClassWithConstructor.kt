package examples

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.LITERAL_MARKER
import dev.patron.STRING_MARKER
import dev.patron.STRING_TEMPLATE_MARKER
import dev.patron.TYPE_MARKER
import dev.patron.builders.file.newFile
import dev.patron.modifiers.Visibility

fun main() {
    val Greeter = ClassName("com.hello.world", "Greeter")
    val Calculator = ClassName("com.hello.world", "Calculator")

    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        classes {
            Greeter {
                constructors {
                    primaryConstructor {
                        parameters {
                            -("name" to String::class.asClassName())
                        }
                    }
                }
                properties {
                    ("name" to String::class.asClassName()) {
                        visibility = Visibility.PRIVATE
                        initAtPrimaryConstructor()
                    }
                }
                functions {
                    "greet" {
                        returning {
                            type = String::class.asClassName()
                        }

                        code {
                            returnWith(
                                valueStatement = "Hello \$name",
                                parameterMarker = STRING_TEMPLATE_MARKER
                            )
                        }
                    }
                }
            }
            Calculator {
                properties {
                    -("num1" to Int::class.asClassName())
                    -("num2" to Int::class.asClassName())
                }
                constructors {
                    constructor {
                        parameters {
                            -("val1" to Int::class.asClassName())
                            -("val2" to Int::class.asClassName())
                        }
                        code {
                            -("this.$LITERAL_MARKER = $LITERAL_MARKER" to listOf(
                                "num1",
                                "val1"
                            ))
                            -("this.$LITERAL_MARKER = $LITERAL_MARKER" to listOf(
                                "num2",
                                "val2"
                            ))
                        }
                    }
                }
                functions {
                    "sum" {
                        returning {
                            type = Int::class.asClassName()
                        }
                        code {
                            -("val sum = %L + %L" to listOf("num1", "num2"))
                            returnWith("sum")
                        }
                    }
                }
            }
        }

        functions {
            "main" {
                code {
                    -("val name = $STRING_MARKER" to listOf("Gustavo"))
                    -("val greeter = $TYPE_MARKER(name)" to listOf(Greeter))
                    -("println(greeter.greet())")
                }
            }
        }
    }.writeTo(System.out)
}
