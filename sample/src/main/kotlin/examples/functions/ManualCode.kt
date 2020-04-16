package examples.functions

import dev.patron.file.newFile

fun main() {
    newFile(
        fileName = "Funcs",
        packageName = "com.hello.world"
    ) {
        newFunction("main") {
            statements {
                +"val text = \"Hello World!\""
                +"println(text)"
            }
        }
    }.writeTo(System.out)
}