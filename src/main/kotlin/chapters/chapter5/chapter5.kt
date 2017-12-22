package chapters.chapter5

fun main(args: Array<String>){
    utils.printTitle(5)

    /* A lambda expression can be moved out of the parentheses if it's the last argument in a function call. If it's the only argument the parentheses 
    can be removed */
    println(people.maxBy { p: Person -> p.age }) 
    println(people.maxBy { p -> p.age }) 
    println(people.maxBy { it.age }) /* Automatic parameter 'it' is generated if the context expects a lambda with one parameter and its type can be inferred */
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
}

data class Person(val name: String, val age: Int)
val people = listOf(Person("Alice", 29), Person("Bob", 31))

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