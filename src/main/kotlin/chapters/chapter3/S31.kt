package chapters.chapter3

fun main(args: Array<String>) {
    printTypes()
}

fun printTypes() {
    /* New ways to create same old Java collections */
    val set = hashSetOf(1, 66, 5)
    val list = arrayListOf(1, 66, 5)
    val map = hashMapOf(1 to "One", 5 to "Five", 66 to "Sixty Six")

    /* Same old Java collections */
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    /* However, Kotlin adds new functionality */
    println(list) /* Invokes toString() */
    println(list.last())
    println(set.max())
    println(list.max())

    /* Check how to add new methods to the String class in StringUtils.kt */
}
