package chapters.chapter2

fun main(args: Array<String>) {
    println(max(1, 3))
    println("Hello ${if(args.size > 0) args[0] else "Kotlin"}!")
}

fun max(a: Int, b: Int) = if(a > b) a else b