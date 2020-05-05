package dev.patron.snapshots

import dev.patron.SnapshotTester
import dev.patron.file.newFile
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
                "privateField".withType(String::class) {
                    isMutable = true
                    visibility = Visibility.PRIVATE
                    isNullable = true
                    initWith = "null"
                }
                "publicField".withType(String::class) {
                    visibility = Visibility.INTERNAL
                    setter {
                        isMutable = true
                        isNullable = true
                        statements {
                            +"privateField = value"
                        }
                        getter {
                            statements {
                                returnWith("privateField")
                            }
                        }
                    }
                }
            }
        }.toString()
    }
}
