package examples.classes

import dev.patron.file.newFile

fun main() {
    newFile(
        fileName = "Classes",
        packageName = "com.hello.world"
    ) {
        newClass("Person") {
            isData = true
            primaryConstructor {
                parameters {
                    "name".withType(String::class) {
                        isProperty = true
                    }
                    "mail".withType(String::class) {
                        isNullable = true
                        isProperty = true
                        initWith = "null"
                    }
                    "id".withType(Int::class) {
                        isProperty = true
                        initWith = "System.currentTimeMillis()"
                    }
                }
            }
        }
    }.writeTo(System.out)
}
