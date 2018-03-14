package chapters.chapter3

import utils.strings.joinToString
import utils.strings.lastChar as last

fun main(args: Array<String>){
    val theList = arrayOf(3, 4, 5, 6)
    println(myListOf(1, 2, *theList))
    println(myListOf(1, 2, 3, 4, 5, 6))

    /*
    Kotlin requires you to explicitly unpack the array, so that every array element 
    becomes a separate argument to the function being called. Spread operator unpacks 
    the array (args) contents
    */
    //fun listOf<T>(vararg values: T): List<T> { ... }
    println(listOf("args: ", *args)) 

    /* infix fun Any.to(other: Any) = Pair(this, other) 
       Check mapping of 1 to 5 */
    val map = mapOf(1 to "One", 5.to("Five"), 66 to "Sixty Six")
    for((index, element) in listOf(map).withIndex()) 
        println("$index: $element")

    for((index, element) in listOf(1 to "One", 5 to "Five", 66 to "Sixty Six").withIndex()) 
        println("$index: $element")

    val (number, name) = 1 to "One" //This is a destructuring declaration
    println("number: $number, name: $name")
}

fun <T> myListOf(vararg theValues: T): List<T>{
    return listOf(*theValues)
}
