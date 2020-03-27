package chapters.chapter5

fun main(args: Array<String>) {
    data class Person(val name: String, val age: Int)

    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Charles", 31), Person("Dan", 21))

    /* Converting lists to sequences does not create intermediate lists returned by map and filter. 
    The elements in a sequence are evaluated lazily */
    println(people.asSequence()
            .map(Person::name)
            .filter { it.startsWith("A") }
            .toList()
    )

    /* Prints nothing. Intermediate operations map and filter are postponed until a terminal 
    operation (toList) is invoked */
    listOf(1, 2, 3, 4).asSequence()
            .map { print("map($it)"); it * it }
            .filter { print("filter($it)"); it % 2 == 0 }

    /* Prints results */
    listOf(1, 2, 3, 4).asSequence()
            .map { print("map($it)"); it * it }
            .filter { print("filter($it)"); it % 2 == 0 }
            .toList()

    /* All elements are mapped */
    println(listOf(1, 2, 3, 4).map { print("map($it)"); it * it }.find { it > 3 })

    /* Only 1 and 2 are mapped */
    println(listOf(1, 2, 3, 4).asSequence().map { print("map($it)"); it * it }.find { it > 3 })

    println(people.asSequence()
            .map(Person::name)
            .filter { it.length < 4 }
            .toList())

    /* It is better to execute filter before map in order to eliminate inappropriate elements 
    as soon as possible */
    println(people.asSequence()
            .filter { it.name.length < 4 }
            .map(Person::name)
            .toList())

    /* Sequences can be generated by providing the first element and a way to get each 
    subsequent element */
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }

    /* Postponed computations related to sequences naturalNumbers and numbersTo100 are 
    executed only when the terminal operation sum is invoked */
    println(numbersTo100.sum())
}