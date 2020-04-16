import dev.patron.file.newFile
import dev.patron.properties.Visibility

fun main() {
    newFile(
        fileName = "HelloWorld",
        packageName = "com.hello.world"
    ) {
        newClass(name = "Greeter") {
            primaryConstructor {
                parameters {
                    "template".withType(String::class.java) {
                        visibility = Visibility.PRIVATE
                        isProperty = true
                    }
                }
            }

            function("greet") {
                parameters {
                    "name" withType String::class.java
                }

                statements {
                    +"println(template.format(name))"
                }
            }
        }

        newFunction("main") {
            parameters {
                "args".withType(String::class.java) {
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
            returning(Int::class.java)
            statements {
                add("var sum = 0")
                controlFlow("for (i in 0 until 10)") {
                    add("var += i")
                }
                returnWith("sum")
            }
        }
    }.writeTo(System.out)
}
