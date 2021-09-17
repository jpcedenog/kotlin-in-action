package chapters.chapter1

fun main() {
    for (i in 1 until 10) {
        println("$i -> " + "${i::class.simpleName}")
    }

    println()

    for (i in 1..10) {
        println("$i -> " + "${i::class.simpleName}")
    }

    val s = "JP Cedeno"

    println(rev1(s))
    println(rev2(s))
}

fun rev1(s: String): String {
    var rev = ""
    for(c in s){
        rev = c + rev
    }
    return rev
}

fun rev2(s: String): String {
    var rev = ""
    for (i in s.length - 1 downTo 0) {
        rev += s[i]
    }
    return rev
}