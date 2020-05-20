package dev.patron.snapshots

import com.squareup.kotlinpoet.ClassName
import dev.patron.SnapshotTester
import dev.patron.builders.enums.newEnum
import dev.patron.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.junit.Test

class EnumTest : SnapshotTester() {

    @Test
    fun buildEnum_withAdd_returnsExpectedValue() = test {
        newFile(
            fileName = "Enums",
            packageName = "com.hello.world"
        ) {
            enums {
                "ViewVisibility" {
                    values {
                        -"Visible"
                        -"Invisible"
                        -"Gone"
                    }
                }
            }
        }.toString()
    }

    @Test
    fun buildEnum_withUnaryPlus_returnsExpectedValue() = test {
        newFile(
            fileName = "Enums",
            packageName = "com.hello.world"
        ) {
            enums {
                "ViewVisibility" {
                    values {
                        -"Visible"
                        -"Invisible"
                        -"Gone"
                    }
                }
            }
        }.toString()
    }

    @Test
    fun buildEnum_withDifferentVisibility_returnsExpectedValue() = test {
        newFile(
            fileName = "Enums",
            packageName = "com.hello.world"
        ) {
            enums {
                "ViewVisibility" {
                    visibility = Visibility.INTERNAL

                    values {
                        -"Visible"
                        -"Invisible"
                        -"Gone"
                    }
                }
            }
        }.toString()
    }

    @Test
    fun buildEnum_withNewEnum_returnsExpectedValue() = test {
        newEnum(ClassName("com.hello.world", "ViewVisibility")) {
            visibility = Visibility.INTERNAL

            values {
                -"Visible"
                -"Invisible"
                -"Gone"
            }
        }.toString()
    }
}
