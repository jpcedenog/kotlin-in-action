package chapters.chapter2

import java.util.TreeMap

fun main(args: Array<String>) {
    //Iterate over a range
    val oneToForty = 1..40
    println("${oneToForty::class.simpleName}")
    for(i in oneToForty) {
        print(fizzBuzz(i))
    }

    println()

    println("\nCount backward")
    for(i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }

    println("\nCount forward")
    for(i in 1 until 100 + 1 step 2) {
        print(fizzBuzz(i))
    }
    
    println()

    iterateOverMap()

    println(isLetter('Q'))
    println(isNotDigit('4'))
    println(recognize('T'))
    println(recognize('5'))
    println(recognize('#'))
    println("Kotlin" in setOf("Java", "Scala"))
    println("Kotlin" in "Java".."Scala") //The same as "Java" <= "Kotlin" && "Kotlin" <= "Scala" 
}

fun fizzBuzz (i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun iterateOverMap() {
    val binaryReps = TreeMap<Char, String>()

    for(c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "Unrecognizable"
}
