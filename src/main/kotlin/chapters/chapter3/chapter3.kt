package chapters.chapter3

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
    override open fun click() = println("Button clicked!")
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
