@file:JvmName(name = "test")

package dev.patron.snapshots

import com.squareup.kotlinpoet.ClassName
import dev.patron.SnapshotTester
import dev.patron.file.newFile
import org.junit.Test
import java.io.IOException

class AnnotationTest : SnapshotTester() {

    @Test
    fun fileAnnotation_withoutArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            annotateWith(JvmName::class)
        }.toString()
    }

    @Test
    fun fileAnnotation_withArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            annotateWith(JvmName::class) {
                "name = %S" withValue "AnnotationTestSnap"
            }
        }.toString()
    }

    @Test
    fun classAnnotation_withoutArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            newClass("Person") {
                isData = true
                primaryConstructor {
                    parameters {
                        "name".withType(String::class) {
                            isProperty = true
                        }
                    }
                }

                annotateWith(ClassName("com.squareup.moshi", "JsonClass"))
            }
        }.toString()
    }

    @Test
    fun classAnnotation_withArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            newClass("Person") {
                isData = true
                primaryConstructor {
                    parameters {
                        "name".withType(String::class) {
                            isProperty = true
                        }
                    }
                }

                annotateWith(ClassName("com.squareup.moshi", "JsonClass")) {
                    "generateAdapter = %B" withValue true
                }
            }
        }.toString()
    }

    @Test
    fun enumAnnotation_withoutArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            enum("ViewVisibility") {
                values {
                    +"Visible"
                    +"Invisible"
                    +"Gone"
                }

                annotateWith(ClassName("com.squareup.moshi", "JsonClass"))
            }
        }.toString()
    }

    @Test
    fun enumAnnotation_withArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            enum("ViewVisibility") {
                values {
                    +"Visible"
                    +"Invisible"
                    +"Gone"
                }

                annotateWith(ClassName("com.squareup.moshi", "JsonClass")) {
                    "generateAdapter = %B" withValue true
                }
            }
        }.toString()
    }

    @Test
    fun functionAnnotation_withoutArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            newClass("TestClass") {
                function("helloWorld") {
                    annotateWith(Test::class)
                }
            }
        }.toString()
    }

    @Test
    fun functionAnnotation_withArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            newClass("TestClass") {
                function("helloWorld") {
                    annotateWith(Test::class) {
                        "expected = %T::class" withValue IOException::class
                    }
                }
            }
        }.toString()
    }

    @Test
    fun parameterAnnotation_withoutArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            newFunction("helloWorld") {
                parameters {
                    "random".withType(Int::class) {
                        initWith = "R.string.hello_world"
                        annotateWith(ClassName("android.support.annotation", "StringRes"))
                    }
                }
            }
        }.toString()
    }

    @Test
    fun parameterAnnotation_withArguments() = test {
        newFile(
            fileName = "Annotations",
            packageName = "com.hello.world"
        ) {
            newFunction("helloWorld") {
                parameters {
                    "random".withType(Int::class) {
                        annotateWith(ClassName("foo.bar", "Lorem")) {
                            "someKey = %S" withValue "someValue"
                        }
                    }
                }
            }
        }.toString()
    }
}
