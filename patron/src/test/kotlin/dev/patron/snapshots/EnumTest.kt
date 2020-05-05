package dev.patron.snapshots

import dev.patron.SnapshotTester
import dev.patron.file.newFile
import dev.patron.modifiers.Visibility
import org.junit.Test

class EnumTest : SnapshotTester() {

    @Test
    fun buildEnum_withAdd_returnsExpectedValue() = test {
        newFile(
            fileName = "Enums",
            packageName = "com.hello.world"
        ) {
            enum("ViewVisibility") {
                values {
                    add("Visible")
                    add("Invisible")
                    add("Gone")
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
            enum("ViewVisibility") {
                values {
                    +"Visible"
                    +"Invisible"
                    +"Gone"
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
            enum("ViewVisibility") {
                visibility = Visibility.INTERNAL

                values {
                    +"Visible"
                    +"Invisible"
                    +"Gone"
                }
            }
        }.toString()
    }
}
