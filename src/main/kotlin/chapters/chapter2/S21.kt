package chapters.chapter2

/* Function main() has a block body */
fun main(args: Array<String>) {
    println(max(1, 3))
    println("Hello ${if(args.size > 0) args[0] else "Kotlin"}!")

    val message: String
    if(canPerformOperation()) {
        message = "Success"
    } else {
        message = "Failed"
    }

    println(message)
}

/* Function max() and canPerformOperation() have an expression body */
fun max(a: Int, b: Int) = if(a > b) a else b

fun canPerformOperation() = true