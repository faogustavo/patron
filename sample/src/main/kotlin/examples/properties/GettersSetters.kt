package examples.properties

import dev.patron.file.newFile
import dev.patron.modifiers.Visibility

fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        properties {
            "privateField".withType(String::class) {
                isMutable = true
                visibility = Visibility.PRIVATE
                isNullable = true
                initWith = "null"
            }
            "publicField".withType(String::class) {
                visibility = Visibility.INTERNAL
                setter {
                    isMutable = true
                    isNullable = true
                    statements {
                        +"privateField = value"
                    }
                    getter {
                        statements {
                            returnWith("privateField")
                        }
                    }
                }
            }
        }
    }.writeTo(System.out)
}