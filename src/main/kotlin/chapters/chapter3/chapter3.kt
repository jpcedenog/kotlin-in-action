package chapters.chapter3

import utils.strings.joinToString
import utils.strings.lastChar as last

fun main(args: Array<String>){
    utils.printTitle(3)

    printTypes()
    println(listOf(1, 3, 4).joinToString(separator="; ", prefix="{", postfix="}"))
    println("Juan Pablo".last())
    println("Juan Pablo".last)
    val view: View = Button()
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
    for((index, element) in listOf(map).withIndex()) 
        println("$index: $element")

    for((index, element) in listOf(1 to "One", 5 to "Five", 66 to "Sixty Six").withIndex()) 
        println("$index: $element")

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
    println(list.max())

    /* Check how to add new methods to the String class in string-utils.kt */
}

open class View {
    open fun click() = println("View clicked!")
}

class Button: View() {
    override fun click() = println("Button clicked!")
}

/* Extension functions cannot be overriden, they depend on 
the declared static type of the variable not on the runtime type */
fun View.showOff() = println("I'm a View!")
fun Button.showOff() = println("I'm a Button!")

fun <T> myListOf(vararg theValues: T): List<T>{
    return listOf(*theValues)
}

fun parsePath(path: String){
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePathRegEx(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)

    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}

class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave(){
    //validate() is a local function
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
    // Save user to the database
}

fun saveUser(user: User){
    user.validateBeforeSave()
}
