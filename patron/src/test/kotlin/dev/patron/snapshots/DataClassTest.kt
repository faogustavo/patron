package dev.patron.snapshots

import com.squareup.kotlinpoet.asClassName
import dev.patron.SnapshotTester
import dev.patron.builders.file.newFile
import org.junit.Test

class DataClassTest : SnapshotTester() {

    @Test
    fun dataClassFields() = test {
        newFile(
            fileName = "Classes",
            packageName = "com.hello.world"
        ) {
            classes {
                "Person" {
                    isData = true

                    properties {
                        ("name" to String::class.asClassName()) {
                            initAtPrimaryConstructor()
                        }
                        ("mail" to String::class.asClassName()) {
                            isNullable = true
                            initAtPrimaryConstructor()
                        }
                        ("id" to Int::class.asClassName()) {
                            initAtPrimaryConstructor()
                        }
                    }

                    constructors {
                        primaryConstructor {
                            parameters {
                                -("name" to String::class.asClassName())
                                ("mail" to String::class.asClassName()) {
                                    isNullable = true
                                    initWithNull()
                                }
                                ("id" to Int::class.asClassName()) {
                                    defaultValue = "System.currentTimeMillis()"
                                }
                            }
                        }
                    }
                }
            }
        }.toString()
    }
}
