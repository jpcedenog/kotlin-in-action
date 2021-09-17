package chapters.chapter3

import utils.strings.lastChar as last

fun main(args: Array<String>) {
    println(listOf(1, 3, 4).joinToString(separator = "; ", prefix = "{", postfix = "}"))
    println("Juan Pablo".last())
    println("Juan Pablo".last)
}
