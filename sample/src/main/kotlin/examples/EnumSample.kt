package examples

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.TYPE_MARKER
import dev.patron.builders.file.newFile
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

        enums {
            "SimpleValues" {
                visibility = Visibility.INTERNAL
                values {
                    -"PAPER"
                    -"SCISSORS"
                    -"ROCK"
                    -"LIZARD"
                    -"SPOCK"
                }
            }

            EnumWithClass {
                values {
                    -"A"
                    -"B"
                    -"C"
                }
                classes {
                    InnerClass {}
                }
                properties {
                    ("foo" to String::class.asClassName()) {
                        isNullable = true
                    }
                }
                objects {
                    "Counter" {
                        properties {
                            ("count" to Int::class.asClassName()) {
                                initWith = 0
                                isMutable = true
                            }
                        }
                    }
                }
                companionObject {
                    newFunction("parse") {
                        returning {
                            type = EnumWithClass
                        }
                        code {
                            returnWith(
                                valueStatement = EnumWithClass,
                                parameterMarker = "$TYPE_MARKER.A"
                            )
                        }
                    }
                }
            }
        }

        classes {
            EnumHolder {
                annotations {
                    -Nullable::class.asClassName()
                }
                enums {
                    BasicEnum {
                        values {
                            -"WIN"
                            -"LOSE"
                        }
                    }
                }
            }
        }

        functions {
            "main" {
                code {
                    -("println($TYPE_MARKER.WIN)" to listOf(BasicEnum))
                }
            }
        }
    }.writeTo(System.out)
}