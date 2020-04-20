package dev.patron.snapshots

import dev.patron.SnapshotTester
import dev.patron.file.newFile
import dev.patron.modifiers.Visibility
import org.junit.Test

class HelloWorldSnapshotTest : SnapshotTester() {

    @Test
    fun buildHelloWorld_returnsExpectedValue() = test {
        newFile(
            fileName = "HelloWorld",
            packageName = "com.hello.world"
        ) {
            newClass(name = "Greeter") {
                primaryConstructor {
                    parameters {
                        "template".withType(String::class) {
                            visibility = Visibility.PRIVATE
                            isProperty = true
                            initWith = "\"Hello %%s\""
                        }
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
        }.toString()
    }
}
