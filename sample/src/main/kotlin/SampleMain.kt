import dev.patron.Visibility
import dev.patron.newFile

fun main() {
    newFile("HelloWorld") {
        newClass("Greeter") {
            visibility = Visibility.PROTECTED
            primaryConstructor {
                parameters {
                    "name".withType(String::class.java) {
                        visibility = Visibility.PRIVATE
                        isProperty = true
                    }
                    "age" withType Int::class.java
                }
            }
        }
    }.writeTo(System.out)
}
