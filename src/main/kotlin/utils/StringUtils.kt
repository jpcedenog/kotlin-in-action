//@file:JvmName("StringFunctions")
package utils.strings

const val SEPARATOR = "\n"

fun <T> Collection<T>.joinToString(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

/* This is an extension function */
fun String.lastChar(): Char = this.get(this.length - 1)

/* This one too */
val myRepeat: String.(Int) -> String = { times -> this.repeat(times) }

/* This is an extension property */
val String.lastChar: Char get() = get(length - 1)
