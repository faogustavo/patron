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
        properties {
            ("NAME" to String::class.asClassName()) {
                isConst = true
                visibility = Visibility.INTERNAL
                initWith = "Simple Class Name"
            }
        }

        newClass("PropertyHolder") {
            properties {
                ("count" to Int::class.asClassName()) {
                    initWith = 0
                    isMutable = true
                }
            }

            functions {
                "inc" {
                    code {
                        -"count += 1"
                    }
                }
            }
        }

        newObject("SingletonObject") {
            properties {
                ("staticProperty" to String::class.asClassName()) {
                    isMutable = true
                    isNullable = true

                    annotateWith(Nullable::class.asClassName())
                    annotateWith(JvmStatic::class.asClassName())
                }
            }
        }

        enums {
            "SampleEnum" {
                values {
                    -"A"
                    -"B"
                    -"C"
                }

                properties {
                    ("isTrue" to Boolean::class.asClassName()) {
                        initWith = false
                    }
                }
            }
        }
    }.writeTo(System.out)
}
