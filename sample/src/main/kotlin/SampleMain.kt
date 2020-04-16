import dev.patron.file.newFile
import dev.patron.modifiers.Visibility

fun main() {
    newFile(
        fileName = "HelloWorld",
        packageName = "com.hello.world"
    ) {
        properties {
            "LOREM_IPSUM".withType(String::class) {
                isMutable = true
                initWith = """"Dolor Sit Amet.""""
            }
        }

        newClass(name = "Greeter") {
            primaryConstructor {
                parameters {
                    "template".withType(String::class) {
                        visibility = Visibility.PRIVATE
                        isProperty = true
                    }
                }
            }

            properties {
                "LOREM_IPSUM".withType(String::class) {
                    isNullable = true
                    isMutable = true
                    initWith = """"Dolor Sit Amet.""""
                }
            }

            function("greet") {
                parameters {
                    "name" withType String::class
                }

                statements {
                    +"println(template.format(name))"
                }
            }
        }

        newFunction("main") {
            parameters {
                "args".withType(String::class) {
                    isVararg = true
                }
            }

            statements {
                +"val template = args[0]"
                +"val name = args[1]"
                +"Greeter(template).greet(name)"
            }
        }

        newFunction("increment") {
            returning(Int::class)
            statements {
                add("var sum = 0")
                controlFlow("for (i in 0 until 10)") {
                    add("sum += i")
                }
                returnWith("sum")
            }
        }
    }.writeTo(System.out)
}
