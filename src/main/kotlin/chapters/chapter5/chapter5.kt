package chapters.chapter5

data class Person(val name: String, val age: Int)
val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Charles", 31), Person("Dan", 21))

data class Book(val title: String, val authors: List<String>) 
val books = listOf(Book("Thursday Next", listOf("Jasper Fforde")), 
                    Book("Mort", listOf("Terry Pratchett")), 
                    Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")))

fun main(args: Array<String>){
    utils.printTitle(5)

    lambdaExpressionsAndMemberReferences()
    functionalAPIsForCollections()
    lazyCollectionOperationsAndSequences()
    lambdasWithReceivers()
}

/**********************************************************************************************************************/
/**********************************************************************************************************************/
/**********************************************************************************************************************/
fun lambdaExpressionsAndMemberReferences() {
    /* 
    Standard joinToString takes a function to convert the object to a customized String version
    In these two cases, there is no lambda context so the 'it' parameter cannot be used 
    */
    val names = people.joinToString(separator = " ", transform = { p: Person -> p.name })
    val names2 = people.joinToString(" ") { p: Person -> p.name }

    println(names)
    println(names2)

    /* 
    A lambda expression can be moved out of the parentheses if it's the last argument in a function call. If it's the only 
    argument the parentheses can be removed 
    */
    println(people.maxBy { p: Person -> p.age }) 
    println(people.maxBy { p -> p.age }) 

    /* Automatic parameter 'it' is generated if the context 
    expects a lambda with one parameter and its type can be inferred */
    println(people.maxBy { it.age }) 

    val sum = { x: Int, y: Int -> 
        println("Computing the sum of $x and $y...")
        x + y 
    }

    println(sum(3, 6))

    /*
    If you use a lambda in a function, you can access the parameters of that function as well as the 
    local variables declared before the lambda
    */
    fun printMessagesWithPrefix(messages: Collection<String>, prefix: String){
        messages.forEach { 
            /* 'prefix' is available within the lambda block */
            println("$prefix $it")
        }
    }
    printMessagesWithPrefix(listOf("Msg1", "Msg2"), "Error: ")

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
    printProblemCounts(listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error"))

    println(people.maxBy(Person::age)) /* Member reference */

    /* Run a function (as a member reference) that isn't part of a class */
    fun salute() = println("Salute!")
    run(::salute)

    val createPerson = ::Person
    fun Person.isAdult() = age >= 21
    val personsIsAdultFunction = Person::isAdult
    val personsAgeFunction = Person::age

    /* An action of creating an instance of 'Person' is saved as a value */
    val alice = createPerson("Alice", 29) 
    println(alice)
    val alicesIsAdultFunction = alice::isAdult
    println(personsIsAdultFunction(alice))
    println(alicesIsAdultFunction())

    val alicesAgeFunction = alice::age
    println(personsAgeFunction(alice))
    println(alicesAgeFunction())
}
/**********************************************************************************************************************/
/**********************************************************************************************************************/
/**********************************************************************************************************************/
fun functionalAPIsForCollections() {
    /* The filter function does not change the underlying collection */
    val list = listOf(1, 2, 3, 4)
    println(list.filter{ it % 2 == 0 })
    println(people.filter{ it.age < 30 })

    /* To change the underlying collection, use the map function */
    println(list.map{ it * it })
    println(people.map{ it.name })
    println(people.map(Person::name))

    /* Don't repeat a calculation if you don’t need to! Simple-looking code using lambda expressions 
    can sometimes obscure the complexity of the underlying operations*/
    val maxAge = people.maxBy(Person::age)
    println(people.filter{ it.age == maxAge?.age })

    /* You can also apply the filter and transformation functions to maps */
    val numbers = mapOf(0 to "zero", 1 to "one")
    /* mapValues returns a new map with entries having the keys of this map and the values obtained by applying the transform 
    function to each entry in this Map */
    println(numbers.mapValues{ it.value.toUpperCase() })
    println(numbers.map{ it.value.toUpperCase() })

    /* Applying a predicate to a collection */
    val canBeInClub30 = { p: Person -> p.age <= 30 }
    println("All in club30? ${people.all(canBeInClub30)}")
    println("Any in club30? ${people.any(canBeInClub30)}")
    println("How many in club30? ${people.count(canBeInClub30)}")
    println("Who is in club30? ${people.find(canBeInClub30)}")
    println("Age groups are: ${people.groupBy{ it.age }}")

    /* Group strings by their first character */
    val stringGroups = listOf("a", "ab", "b")
    println(stringGroups.groupBy(String::first))

    /* The flatMap function does two things: At first it transforms (or maps) each element to a 
    collection according to the function given as an argument, and then it combines (or flattens) 
    several lists into one */
    println(books.flatMap{ it.authors })
    println(books.flatMap{ it.authors }.toSet()) /* toSet() removes duplicates */
}
/**********************************************************************************************************************/
/**********************************************************************************************************************/
/**********************************************************************************************************************/
fun lazyCollectionOperationsAndSequences() {
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
/**********************************************************************************************************************/
/**********************************************************************************************************************/
/**********************************************************************************************************************/
fun lambdasWithReceivers() {
    /* The with function converts its first argument into a receiver of the lambda that’s
    passed as a second argument. You can access this receiver via an explicit this reference.
    Alternatively, as usual for a this reference, you can omit it and access methods
    or properties of this value without any additional qualifiers */
    fun alphabet() = with(StringBuilder()) {
        for(letter in 'A'..'Z'){
            append(letter)
        }
        append("\nNow I know the alphabet")
        toString() /* the last expression of the lambda gets returned */
        /* this@OuterClass.toString() */
    }

    /* One of many cases where this is useful is when you’re creating an instance of an
    object and need to initialize some properties right away. In Java, this is usually accomplished
    through a separate Builder object; and in Kotlin, you can use apply on any
    object without any special support from the library where the object is defined */
    fun alphabet2() = StringBuilder().apply {
        for (letter in 'A'..'Z') {
        append(letter)
        }
        append("\nNow I know the alphabet!")
    }.toString()

    /* The buildString function is an elegant solution for the task of creating a String
    with the help of StringBuilder */
    fun alphabet3() = buildString {
        for (letter in 'A'..'Z') {
        append(letter)
        }
        append("\nNow I know the alphabet!")
    }

    println(alphabet())
    println(alphabet2())
    println(alphabet3())
}
/**********************************************************************************************************************/
/**********************************************************************************************************************/
/**********************************************************************************************************************/