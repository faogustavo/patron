package examples

import com.squareup.kotlinpoet.asClassName
import dev.patron.builders.file.newFile
import dev.patron.modifiers.Visibility

fun main() {
    newFile(
        fileName = "HelloWorld",
        packageName = "com.hello.world"
    ) {
        classes {
            "Greeter" {
                properties {
                    ("template" to String::class.asClassName()) {
                        visibility = Visibility.PRIVATE
                        initAtPrimaryConstructor()
                    }
                }

                constructors {
                    primaryConstructor {
                        parameters {
                            ("template" to String::class.asClassName()) {
                                defaultValue = "Hello %s"
                            }
                        }
                    }
                }

                functions {
                    "greet" {
                        parameters {
                            -("name" to String::class.asClassName())
                        }

                        code {
                            -"println(template.format(name))"
                        }
                    }
                }
            }
        }

        functions {
            "main" {
                parameters {
                    ("args" to String::class.asClassName()) {
                        isVarargs = true
                    }
                }

                code {
                    -"val template = args[0]"
                    -"val name = args[1]"
                    -"Greeter(template).greet(name)"
                }
            }
        }
    }.writeTo(System.out)
}
