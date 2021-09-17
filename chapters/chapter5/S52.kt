package chapters.chapter5

fun main(args: Array<String>) {
    data class Person(val name: String, val age: Int)

    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Charles", 31), Person("Dan", 21))

    data class Book(val title: String, val authors: List<String>)

    val books = listOf(Book("Thursday Next", listOf("Jasper Fforde")),
            Book("Mort", listOf("Terry Pratchett")),
            Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")))

    /* The filter function does not change the underlying collection */
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 })
    println(people.filter { it.age < 30 })

    /* To change the underlying collection, use the map function */
    println(list.map { it * it })
    println(people.map { it.name })
    println(people.map(Person::name))

    /* Don't repeat a calculation if you don’t need to! Simple-looking code using lambda expressions 
    can sometimes obscure the complexity of the underlying operations*/
    val maxAge = people.maxBy(Person::age)
    println(people.filter { it.age == maxAge?.age })

    /* You can also apply the filter and transformation functions to maps */
    val numbers = mapOf(0 to "zero", 1 to "one")
    /* mapValues returns a new map with entries having the keys of this map and the values obtained by applying the transform 
    function to each entry in this Map */
    println(numbers.mapValues { it.value.toUpperCase() })
    println(numbers.map { it.value.toUpperCase() })

    /* Applying a predicate to a collection */
    val canBeInClub30 = { p: Person -> p.age <= 30 }
    println("All in club30? ${people.all(canBeInClub30)}")
    println("Any in club30? ${people.any(canBeInClub30)}")
    println("How many in club30? ${people.count(canBeInClub30)}")
    println("Who is in club30? ${people.find(canBeInClub30)}")
    println("Age groups are: ${people.groupBy { it.age }}")

    /* Group strings by their first character */
    val stringGroups = listOf("a", "ab", "b")
    println(stringGroups.groupBy(String::first))

    /* The flatMap function does two things: At first it transforms (or maps) each element to a 
    collection according to the function given as an argument, and then it combines (or flattens) 
    several lists into one */
    println(books.flatMap { it.authors })
    println(books.flatMap { it.authors }.toSet()) /* toSet() removes duplicates */
}