package chapters.chapter5

fun main(args: Array<String>){
    println("*****************\n\n")
    println("*** CHAPTER 5 ***\n\n")
    println("*****************\n\n")

    /* These two expressions do the same thing */
    println(people.maxBy { it.age })
    println(people.maxBy(Person::age))
}

data class Person(val name: String, val age: Int)
val people = listOf(Person("Alice", 29), Person("Bob", 31))