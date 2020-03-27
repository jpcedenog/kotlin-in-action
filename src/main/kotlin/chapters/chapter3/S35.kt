package chapters.chapter3

fun main(args: Array<String>) {
    /* In Kotlin, it is always clear whether split is getting a String or a Regex */
    println("12.345-6.A".split("\\.|-".toRegex()))
    println("12.345-6.A".split(".", "-"))

    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    parsePathRegEx("/Users/yole/kotlin-book/chapter.adoc")

    /* A triple-quoted string can contain line breaks, but you can't use special characters like \n. */
    val kotlinLogo = """| //
                        .|//
                        .|/ \"""

    println(kotlinLogo.trimMargin("."))
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePathRegEx(path: String) {
    /* With triple quoted strings, it is not necessary to escape any characters */
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)

    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}

