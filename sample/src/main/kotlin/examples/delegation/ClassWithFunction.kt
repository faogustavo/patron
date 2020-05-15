package examples.delegation

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.STRING_MARKER
import dev.patron.dsl.TYPE_MARKER
import dev.patron.dsl.builders.file.newFile
import dev.patron.modifiers.Visibility

// Example from the new DSL
fun main() {
    val greeterClassName = ClassName("com.hello.world", "Greeter")
    val innerGreeterClassName = ClassName("com.hello.world", "Greeter.InnerGreeter")

    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        newClass(greeterClassName) {
            newFunction("greet") {
                visibility = Visibility.INTERNAL

                returnType = String::class.asClassName()

                code {
                    !(STRING_MARKER to "Hello from greeter!")
                }
            }

            newClass(innerGreeterClassName) {
                newFunction("innerGreet") {
                    visibility = Visibility.INTERNAL

                    returnType = String::class.asClassName()

                    code {
                        !(STRING_MARKER to "Hello from inner greeter!")
                    }
                }
            }
        }

        newFunction("main") {
            code {
                -("val greeter = $TYPE_MARKER()" to listOf(greeterClassName))
                -"println(greeter.greet())"
                -""
                -("val innerGreeter = $TYPE_MARKER()" to listOf(innerGreeterClassName))
                -"println(innerGreeter.innerGreet())"
            }
        }
    }.writeTo(System.out)
}
