package examples.functions

import dev.patron.file.newFile
import getResourceFile

fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        newFunction("main") {
            codeFrom("example.function.body.basic.txt".getResourceFile())
        }
    }.writeTo(System.out)
}
