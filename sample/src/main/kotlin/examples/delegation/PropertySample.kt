package examples.delegation

import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.jetbrains.annotations.Nullable

fun main() {
    newFile(
        fileName = "Property",
        packageName = "com.hello.world"
    ) {
        newProperty("NAME", String::class.asClassName()) {
            isConst = true
            visibility = Visibility.INTERNAL
            initWith = "Simple Class Name"
        }

        newClass("PropertyHolder") {
            newProperty("count", Int::class.asClassName()) {
                initWith = 0
                isMutable = true
            }

            newFunction("inc") {
                code {
                    -"count += 1"
                }
            }
        }

        newObject("SingletonObject") {
            newProperty("staticProperty", String::class.asClassName()) {
                isMutable = true
                isNullable = true

                annotateWith(Nullable::class.asClassName())
                annotateWith(JvmStatic::class.asClassName())
            }
        }

        newEnum("SampleEnum") {
            values {
                -"A"
                -"B"
                -"C"
            }

            newProperty("isTrue", Boolean::class.asClassName()) {
                initWith = false
            }
        }
    }.writeTo(System.out)
}
