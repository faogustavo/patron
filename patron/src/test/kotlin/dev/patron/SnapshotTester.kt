package dev.patron

import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.Before
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.File

abstract class SnapshotTester(private val resourcesDir: String? = null) {

    @Before
    fun setUp() {
        clearAllMocks()
        unmockkAll()
    }

    private val metadata: MetaData by lazy {
        val currentStacktrace = Thread.currentThread().stackTrace.getOrNull(6)
        check(currentStacktrace != null) { "Unable to get metadata from this file" }

        MetaData(currentStacktrace.className, currentStacktrace.methodName)
    }

    fun test(block: () -> String) {
        val expectedResult = getSnapshotExpectedResult(metadata).normalizeLineEndingChar()
        val result = block().normalizeLineEndingChar()

        expectThat(expectedResult)
            .isEqualTo(result)
    }

    fun getFileFromResources(fileName: String, vararg path: String): File {
        val basePath = listOf(
            "src",
            "test",
            "resources"
        )

        val completePath = basePath + path.toList() + fileName
        val pathString = completePath.joinToString(File.separator)

        return File(pathString)
    }

    private fun getSnapshotExpectedResult(metaData: MetaData): String {
        val file = getFileFromResources(metaData.methodName.snap, resourcesDir ?: metadata.className.packageAsPath)

        check(file.exists()) {
            "Failed to load resource for this test. Check if you created the snapshot file at ${file.path}"
        }

        return file.readText()
    }

    private fun List<String>.filterEmpty() = filter { !it.isEmpty() }

    private fun String.normalizeLineEndingChar() = lines().joinToString("\n")

    private val String.snap
        get() = "$this.snap"

    private val String.packageAsPath
        get() = replace('.', File.separatorChar)

    private class MetaData(
        val className: String,
        val methodName: String
    )
}
