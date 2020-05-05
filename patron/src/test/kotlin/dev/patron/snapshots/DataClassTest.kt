package dev.patron.snapshots

import dev.patron.SnapshotTester
import dev.patron.file.newFile
import org.junit.Test

class DataClassTest : SnapshotTester() {

    @Test
    fun dataClassFields() = test {
        newFile(
            fileName = "Classes",
            packageName = "com.hello.world"
        ) {
            newClass("Person") {
                isData = true
                primaryConstructor {
                    parameters {
                        "name".withType(String::class) {
                            isProperty = true
                        }
                        "mail".withType(String::class) {
                            isNullable = true
                            isProperty = true
                            initWith = "null"
                        }
                        "id".withType(Int::class) {
                            isProperty = true
                            initWith = "System.currentTimeMillis()"
                        }
                    }
                }
            }
        }.toString()
    }
}
