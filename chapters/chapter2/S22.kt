package chapters.chapter2

fun main(args: Array<String>) {
    val rectangle = Rectangle(2, 3)
    println("Is a square? ${rectangle.isSquare}")

    val square = Rectangle(8, 8)
    println("Is a square? ${square.isSquare}")
}

class Person(
        val name: String, /* generates trivial getter */
        var isMarried: Boolean /* generates getter and setter */
)

/* This is a data class (used to create value objects) */
class Rectangle(val height: Int, val width: Int) {
    /* Custom accessor, no need to store information in separate field */
    val isSquare: Boolean get() = height == width
}