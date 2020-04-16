package examples.functions

import dev.patron.file.newFile
import readResourceFile

fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        newFunction("main") {
            codeFrom("example.function.body.basic.txt".readResourceFile())
        }
    }.writeTo(System.out)
}
