package chapters.chapter2

import java.util.TreeMap
import java.io.BufferedReader
import java.io.StringReader
import utils.Color
import utils.Color.*

fun main(args: Array<String>){
    utils.printTitle(2)

    functionAndVariables(args)
    classesAndProperties()
    representingChoices()
    iteratingOverThings()
    exceptionsInKotlin()
}

fun functionAndVariables(args: Array<String>) {
    fun max(a: Int, b: Int) = if(a > b) a else b
    println(max(1, 3))
    println("Hello ${if(args.size > 0) args[0] else "Kotlin"}!")
}

fun classesAndProperties() {
    class Rectangle (val height: Int, val width: Int) {
        /* Custom accessor */
        val isSquare: Boolean get() = height == width
    }

    val rectangle = Rectangle(2, 3)
    println("Is a square? ${rectangle.isSquare}")

    val rectangle2 = Rectangle(8, 8)
    println("Is a square? ${rectangle2.isSquare}")
}

interface Expr

fun representingChoices() {
    fun getMnemonic(color: Color) = when (color) {
        RED -> "Richard"
        ORANGE -> "Of"
        YELLOW -> "York"
        GREEN -> "Gave"
        BLUE -> "Battle"
        INDIGO -> "In"
        VIOLET -> "Vain"
    }

    fun getWarmth(color: Color) = when(color) {
        RED, ORANGE, YELLOW -> "warm"
        GREEN -> "neutral"
        BLUE, INDIGO, VIOLET -> "cold"
    }

    fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }

    fun mixOptimized(c1: Color, c2: Color) = when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
        (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
        else -> throw Exception("Dirty color")
    }

    println(getMnemonic(BLUE))
    println(getWarmth(ORANGE))
    println(mix(BLUE, YELLOW))
    try {
        println(mix(Color.RED, Color.VIOLET)) //Throws an exception! Catch it!
    }catch(e: Exception){
        println("Exception caught! ${e.message}")
    }
    println(mixOptimized(BLUE, YELLOW))

    class Num(val value: Int): Expr
    class Sum(val left: Expr, val right: Expr): Expr

    fun eval(e: Expr): Int = when(e) {
        is Num -> {
            println("num: ${e.value}")
            e.value //last expression is returned
        }
        is Sum -> {
            val left = eval(e.left)
            val right = eval(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

fun iteratingOverThings() {
    fun fizzBuzz (i: Int) = when {
        i % 15 == 0 -> "FizzBuzz "
        i % 3 == 0 -> "Fizz "
        i % 5 == 0 -> "Buzz "
        else -> "$i "
    }

    val oneToForty = 1..40
    for(i in oneToForty) {
        print(fizzBuzz(i))
    }
    println()
    for(i in 1..40) {
        print(fizzBuzz(i))
    }

    println("\nCount backward")
    for(i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }

    println("\nCount forward")
    for(i in 1 until 100 + 1 step 2) {
        print(fizzBuzz(i))
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
    println()
    iterateOverMap()

    fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
    fun isNotDigit(c: Char) = c !in '0'..'9'
    fun recognize(c: Char) = when(c) {
        in '0'..'9' -> "It's a digit"
        in 'a'..'z', in 'A'..'Z' -> "It's a letter"
        else -> "Unrecognizable"
    }

    println(isLetter('Q'))
    println(isNotDigit('4'))
    println(recognize('T'))
    println(recognize('5'))
    println(recognize('#'))
    println("Kotlin" in setOf("Java", "Scala"))
    println("Kotlin" in "Java".."Scala") //The same as "Java" <= "Kotlin" && "Kotlin" <= "Scala" 
}

fun exceptionsInKotlin(){
    fun readNumber(reader: BufferedReader): Int? {
        try {
            val line = reader.readLine()
            return Integer.parseInt(line)
        }
        catch (e: NumberFormatException) {
            return null
        } finally {
            reader.close()
        }
    }

    /* The function below uses "try" as an expression */
    fun readNumberExpr(reader: BufferedReader) {
        val number = try {
            Integer.parseInt(reader.readLine())
        } catch (e: NumberFormatException) {
            println("The number is NaN")
            return
        }
        println("The number is: $number")
    }

    println(readNumber(BufferedReader(StringReader("7"))))
    println(readNumber(BufferedReader(StringReader("foo"))))

    readNumberExpr(BufferedReader(StringReader("7")))
    readNumberExpr(BufferedReader(StringReader("foo")))
}