package chapters.chapter5

fun main(args: Array<String>){
    utils.printTitle(5)

    /* A lambda expression can be moved out of the parentheses if 
    it's the last argument in a function call. If it's the only 
    argument the parentheses can be removed */
    println(people.maxBy { p: Person -> p.age }) 

    println(people.maxBy { p -> p.age }) 

    /* Automatic parameter 'it' is generated if the context 
    expects a lambda with one parameter and its type can be inferred */
    println(people.maxBy { it.age }) 

    println(people.maxBy(Person::age)) /* Member reference */

    println(sum(3, 6))

    println(names)
    println(names2)

    printMessagesWithPrefix(listOf("Msg1", "Msg2"), "Error: ")

    printProblemCounts(listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error"))

    /* Run a function (as a member reference) that isn't part of a class */
    run(::salute)

    val alice = createPerson("Alice", 29) 
    println(alice)

    val alicesIsAdultFunction = alice::isAdult
    println(personsIsAdultFunction(alice))
    println(alicesIsAdultFunction())

    val alicesAgeFunction = alice::age
    println(personsAgeFunction(alice))
    println(alicesAgeFunction())

    /* The filter function does not change the underlying collection */
    val list = listOf(1, 2, 3, 4)
    println(list.filter{ it % 2 == 0 })
    println(people.filter{ it.age < 30 })
    
    /* To change the underlying collection, use the map function */
    println(list.map{ it * it })
    println(people.map{ it.name })
    println(people.map(Person::name))

    val maxAge = people.maxBy(Person::age)
    println(people.filter{ it.age == maxAge?.age })

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues{ it.value.toUpperCase() })

    val canBeInClub30 = { p: Person -> p.age <= 30 }
    println("All in club30? ${people.all(canBeInClub30)}")
    println("Any in club30? ${people.any(canBeInClub30)}")
    println("Who is in club30? ${people.find(canBeInClub30)}")

    println("Age groups are: ${people.groupBy{ it.age }}")

    val stringGroups = listOf("a", "ab", "b")
    println(stringGroups.groupBy(String::first))

    println(books.flatMap{ it.authors })
    println(books.flatMap{ it.authors }.toSet())
}

data class Person(val name: String, val age: Int)
val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Carol", 31))

data class Book(val title: String, val authors: List<String>) 
val books = listOf(Book("Thursday Next", listOf("Jasper Fforde")), 
                    Book("Mort", listOf("Terry Pratchett")), 
                    Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")))

val sum = { x: Int, y: Int -> 
    println("Computing the sum of $x and $y...")
    x + y 
}

/* Standard joinToString takes a function to convert the object to a customized String version */
/* In these two cases, there is no lambda context so the 'it' parameter cannot be used */
val names = people.joinToString(separator = " ", transform = { p: Person -> p.name })
val names2 = people.joinToString(" ") { p: Person -> p.name }

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String){
    messages.forEach { 
        println("$prefix $it")
    }
}

/* Kotlin, unlike Java, allows you to access non-final variables and even modify them in a lambda. 
External variables accessed from a lambda, such as prefix, clientErrors, and serverErrors in these 
examples, are said to be captured by the lambda */
fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}

fun salute() = println("Salute!")

val createPerson = ::Person

fun Person.isAdult() = age >= 21
val personsIsAdultFunction = Person::isAdult
val personsAgeFunction = Person::age

