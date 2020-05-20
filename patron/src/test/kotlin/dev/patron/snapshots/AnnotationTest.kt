@file:JvmName(name = "test")

package dev.patron.snapshots

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import dev.patron.LITERAL_MARKER
import dev.patron.STRING_MARKER
import dev.patron.SnapshotTester
import dev.patron.TYPE_MARKER
import dev.patron.builders.classes.newClass
import dev.patron.builders.enums.newEnum
import dev.patron.builders.file.newFile
import dev.patron.builders.function.newFunction
import org.junit.Test
import java.io.IOException

class AnnotationTest : SnapshotTester() {

    @Test
    fun fileAnnotation_withoutArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            annotations {
                -JvmName::class.asClassName()
            }
        }.toString()
    }

    @Test
    fun fileAnnotation_withArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            annotations {
                (JvmName::class.asClassName()) {
                    "name = $STRING_MARKER" withValue "AnnotationTestSnap"
                }
            }
        }.toString()
    }

    @Test
    fun classAnnotation_withoutArguments() = test {
        newClass(ClassName("com.hello.world", "Person")) {
            isData = true

            properties {
                ("name" to String::class.asClassName()) {
                    initAtPrimaryConstructor()
                }
            }

            constructors {
                primaryConstructor {
                    parameters {
                        -("name" to String::class.asClassName())
                    }
                }
            }

            annotations {
                -ClassName("com.squareup.moshi", "JsonClass")
            }
        }.toString()
    }

    @Test
    fun classAnnotation_withArguments() = test {
        newClass(ClassName("com.hello.world", "Person")) {
            isData = true

            properties {
                ("name" to String::class.asClassName()) {
                    initAtPrimaryConstructor()
                }
            }

            constructors {
                primaryConstructor {
                    parameters {
                        -("name" to String::class.asClassName())
                    }
                }
            }

            annotations {
                (ClassName("com.squareup.moshi", "JsonClass")) {
                    "generateAdapter = $LITERAL_MARKER" withValue true
                }
            }
        }.toString()
    }

    @Test
    fun enumAnnotation_withoutArguments() = test {
        newEnum(ClassName("com.hello.world", "ViewVisibility")) {
            values {
                -"Visible"
                -"Invisible"
                -"Gone"
            }

            annotations {
                -ClassName("com.squareup.moshi", "JsonClass")
            }
        }.toString()
    }

    @Test
    fun enumAnnotation_withArguments() = test {
        newEnum(ClassName("com.hello.world", "ViewVisibility")) {
            values {
                -"Visible"
                -"Invisible"
                -"Gone"
            }

            annotations {
                (ClassName("com.squareup.moshi", "JsonClass")) {
                    "generateAdapter = $LITERAL_MARKER" withValue true
                }
            }
        }.toString()
    }

    @Test
    fun functionAnnotation_withoutArguments() = test {
        newClass(ClassName("com.hello.world", "TestClass")) {
            functions {
                "helloWorld" {
                    annotations {
                        -Test::class.asClassName()
                    }
                }
            }
        }.toString()
    }

    @Test
    fun functionAnnotation_withArguments() = test {
        newClass(ClassName("com.hello.world", "TestClass")) {
            functions {
                "helloWorld" {
                    annotations {
                        (Test::class.asClassName()) {
                            "expected = $TYPE_MARKER::class" withValue IOException::class
                        }
                    }
                }
            }
        }.toString()
    }

    @Test
    fun parameterAnnotation_withoutArguments() = test {
        newFunction(
            fileName = "Annotations",
            packageName = "com.hello.world",
            functionName = "helloWorld"
        ) {
            parameters {
                ("random" to Int::class.asClassName()) {
                    defaultValue = "R.string.hello_world"
                    annotations {
                        -ClassName("android.support.annotation", "StringRes")
                    }
                }
            }
        }.toString()
    }

    @Test
    fun parameterAnnotation_withArguments() = test {
        newFunction(
            fileName = "Annotations",
            packageName = "com.hello.world",
            functionName = "helloWorld"
        ) {
            parameters {
                ("random" to Int::class.asClassName()) {
                    annotations {
                        (ClassName("foo.bar", "Lorem")) {
                            "someKey = $STRING_MARKER" withValue "someValue"
                        }
                    }
                }
            }
        }.toString()
    }
}
