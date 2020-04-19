import java.io.File

fun String.getResourceFile() =
    File(listOf("sample", "src", "main", "resources", this).joinToString(File.separator))

fun String.readResourceFile() =
    getResourceFile().readText()
