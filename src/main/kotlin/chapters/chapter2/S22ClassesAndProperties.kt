package chapters.chapter2

fun main(args: Array<String>) {
    val rectangle = Rectangle(2, 3)
    println("Is a square? ${rectangle.isSquare}")

    val square = Rectangle(8, 8)
    println("Is a square? ${square.isSquare}")
}

class Rectangle (val height: Int, val width: Int) {
    /* Custom accessor */
    val isSquare: Boolean get() = height == width
}
