package examples.delegation

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.dsl.TYPE_MARKER
import dev.patron.dsl.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.jetbrains.annotations.Nullable

fun main() {
    newFile(
        fileName = "Enums",
        packageName = "com.hello.world"
    ) {
        val EnumHolder = ClassName("com.hello.world", "EnumHolder")
        val BasicEnum = ClassName("com.hello.world", "EnumHolder.BasicEnum")

        val EnumWithClass = ClassName("com.hello.world", "EnumClass")
        val InnerClass = ClassName("com.hello.world", "EnumClass.InnerClass")

        newEnum("SimpleValues") {
            visibility = Visibility.INTERNAL
            values {
                -"PAPER"
                -"SCISSORS"
                -"ROCK"
                -"LIZARD"
                -"SPOCK"
            }
        }

        newClass(EnumHolder) {
            annotateWith(Nullable::class.asClassName())
            newEnum(BasicEnum) {
                values {
                    -"WIN"
                    -"LOSE"
                }
            }
        }

        newEnum(EnumWithClass) {
            values {
                -"A"
                -"B"
                -"C"
            }
            newClass(InnerClass) {}
            newProperty("foo", String::class.asClassName()) {
                isNullable = true
            }
            newObject("Counter") {
                newProperty("count", Int::class.asClassName()) {
                    initWith = 0
                    isMutable = true
                }
            }
            companionObject {
                newFunction("parse") {
                    returnType = EnumWithClass
                    code {
                        returnWith(
                            valueStatement = EnumWithClass,
                            parameterMarker = "$TYPE_MARKER.A"
                        )
                    }
                }
            }
        }

        newFunction("main") {
            code {
                -("println($TYPE_MARKER.WIN)" to listOf(BasicEnum))
            }
        }
    }.writeTo(System.out)
}
