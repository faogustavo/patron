package examples.functions

import dev.patron.file.newFile

fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        newFunction("buildPerson") {
            parameters {
                "name" withType String::class
                "age".withType(Int::class) {
                    isNullable = true
                    initWith = "null"
                }
            }
        }
    }.writeTo(System.out)
}