package chapters.chapter3

fun main(args: Array<String>){
    /* infix fun Any.to(other: Any) = Pair(this, other) 
       Check mapping of 1 to 5 */
    val map = mapOf(1 to "One", 5.to("Five"), 66 to "Sixty Six")
    for((index, element) in listOf(map).withIndex()) 
        println("$index: $element")

    for((index, element) in listOf(1 to "One", 5 to "Five", 66 to "Sixty Six").withIndex()) 
        println("$index: $element")

    val (number, name) = 1 to "One" //This is a destructuring declaration
    println("number: $number, name: $name")

    /* In Kotlin, it is always clear whether split is getting a String or a Regex */
    println("12.345-6.A".split("\\.|-".toRegex()))
    println("12.345-6.A".split(".", "-"))

    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    parsePathRegEx("/Users/yole/kotlin-book/chapter.adoc")

    /*
    val kotlinLogo = """| //
    .|//
    .|/ \"""

    println(kotlinLogo.trimMargin("."))
    */
}

fun parsePath(path: String){
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

