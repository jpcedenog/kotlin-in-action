package chapters.chapter1

fun main(args: Array<String>){
    utils.printTitle(1)

    println(max(1, 3))
}

fun max(a: Int, b: Int) = if(a > b) a else b

