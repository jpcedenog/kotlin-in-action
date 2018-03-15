package chapters.chapter2

import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>){
    println(readNumber(BufferedReader(StringReader("7"))))
    println(readNumber(BufferedReader(StringReader("foo"))))

    readNumberExpr(BufferedReader(StringReader("7")))
    readNumberExpr(BufferedReader(StringReader("foo")))
}

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
