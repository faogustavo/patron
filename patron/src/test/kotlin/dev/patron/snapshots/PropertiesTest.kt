package dev.patron.snapshots

import com.squareup.kotlinpoet.asClassName
import dev.patron.SnapshotTester
import dev.patron.builders.file.newFile
import dev.patron.modifiers.Visibility
import org.junit.Test

class PropertiesTest : SnapshotTester() {

    @Test
    fun getAndSetterTest() = test {
        newFile(
            fileName = "Funcs",
            packageName = "com.hello.world"
        ) {
            properties {
                ("privateField" to String::class.asClassName()) {
                    isMutable = true
                    visibility = Visibility.PRIVATE
                    isNullable = true
                }
                ("publicField" to String::class.asClassName()) {
                    visibility = Visibility.INTERNAL
                    isMutable = true
                    isNullable = true

                    setter {
                        parameters {
                            -("value" to String::class.asClassName())
                        }
                        code {
                            -"privateField = value"
                        }
                    }
                    getter {
                        code {
                            returnWith("privateField")
                        }
                    }
                }
            }
        }.toString()
    }
}
