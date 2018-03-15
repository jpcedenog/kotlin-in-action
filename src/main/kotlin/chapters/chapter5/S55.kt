package chapters.chapter5

fun main(args: Array<String>) {
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