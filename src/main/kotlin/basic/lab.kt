package basic

import java.io.BufferedReader
import java.io.StringReader
import utils.strings.joinToString
import utils.strings.lastChar as last
import chapters.chapter1.*
import chapters.chapter2.*
import utils.Color
import utils.Color.*
import chapters.chapter3.*
import chapters.chapter3.Button as Button3
import chapters.chapter4.*
import chapters.chapter4.Button

fun main(args: Array<String>){
    println()
    println()

    //chapter2(args)
    //chapter3(args)
    chapter4()

    println()
    println()
}

/*****************************************************************************/
/*************************** CHAPTER 2 ***************************************/
/*****************************************************************************/
fun chapter2(args: Array<String>){
    println(max(1, 3))
    println("The name is ${if(args.size > 0) args[0] else "otherName"}!")
    val rectangle = Rectangle(2, 3)
    println("Is a square? ${rectangle.isSquare}")
    println(getMnemonic(BLUE))
    println(getWarmth(ORANGE))
    println(mix(BLUE, YELLOW))
    println(mixOptimized(BLUE, YELLOW))
    try {
        println(mix(Color.RED, Color.VIOLET)) //Throws an exception! Catch it!
    }catch(e: Exception){
        println("Exception caught! ${e.message}")
    }

    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))

    println()
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
    
    println()
    iterateOverMap()

    println(isLetter('Q'))
    println(isNotDigit('4'))
    println(recognize('T'))
    println(recognize('5'))
    println(recognize('#'))
    readNumberExpr(BufferedReader(StringReader("7")))
    readNumberExpr(BufferedReader(StringReader("foo")))

    println("Kotlin" in setOf("Java", "Scala"))
    println("Kotlin" in "Java".."Scala") //The same as "Java" <= "Kotlin" && "Kotlin" <= "Scala" 
}

/*****************************************************************************/
/*************************** CHAPTER 3 ***************************************/
/*****************************************************************************/
fun chapter3(args: Array<String>){

    /* Also check string-utils.kt file */

    printTypes()
    println(listOf(1, 3, 4).joinToString(separator="; ", prefix="{", postfix="}"))
    println("Juan Pablo".last())
    println("Juan Pablo".last)
    val view: View = Button3()
    view.click()
    view.showOff() /* Extension functions cannot be overriden */

    val theList = arrayOf(1, 2, 3, 4)
    println(myListOf(*theList))
    println(myListOf(1, 2, 3, 4))

    //fun listOf<T>(vararg values: T): List<T> { ... }
    println(listOf("args: ", *args)) //Spread operator unpacks the array (args) contents

    /* infix fun Any.to(other: Any) = Pair(this, other) 
       Check mapping of 1 to 5 */
    val map = mapOf(1 to "One", 5.to("Five"), 66 to "Sixty Six")
    for((index, element) in listOf(1 to "One", 5 to "Five", 66 to "Sixty Six").withIndex()) {
        println("$index: $element")
    }

    val (number, name) = 1 to "One" //This is a destructuring declaration
    println("number: $number, name: $name")

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

    try {
        saveUser(User(1, "", ""))
    }catch(e: Exception){
        println(e.message)
    }
}

/*****************************************************************************/
/*************************** CHAPTER 4 ***************************************/
/*****************************************************************************/
fun chapter4(){
    val button = Button()
    button.click()
    button.setFocus(true)
    button.showOff()
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))

    /* This is default constructor created by the compiler */
    val classWithDefaultValues = ClassWithDefaultValues() 
    println("${classWithDefaultValues.first} ${classWithDefaultValues.last}")

    val userFoo = UserFoo("JP Cedeno")
    userFoo.address = "1709 Vinings Pkwy"
    println(userFoo.address)
    userFoo.address = "1709 Vinings Pkwy"
    println(userFoo.address)

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Mr JP")
    //lengthCounter.counter++ /* Illegal. Setter is private in LengthCounter */
    println(lengthCounter.counter)

    val bob = Client("Bob", 973293)
    println(bob)
    println(bob.copy(postalCode=382555))
}

