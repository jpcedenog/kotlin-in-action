package chapters.chapter1

fun main() {
    for(i in 1 until 10){
       println("$i -> " + "${i::class.simpleName}")
    }

    println()

    for(i in 1..10){
       println("$i -> " + "${i::class.simpleName}")
    }
}