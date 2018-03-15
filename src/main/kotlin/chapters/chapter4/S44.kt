package chapters.chapter4

import java.io.File

fun main(args: Array<String>){
    /* Examples of how to use a singleton */
    Payroll.allEmployees.add(Person("JP Cedeno", "foo foo", 100000.0))
    Payroll.allEmployees.add(Person("JP Cedeno", "foo foo", 100000.toDouble()))
    println(Payroll.allEmployees[1])
    Payroll.calculateSalary()

    A.SomeCompanionObject.bar()
    runSomeInterface(A)
    A.foo()
    A.SomeCompanionObject.foo()

    User2.foo("Message to companion object")
 
    /* Object expression. This is equivalent to Java's anonymous classes */
    val listener: SomeInterface<String> = object: SomeInterface<String>, AnotherInterface<String> {
        override fun bar() = println("Implementing object expression 1")
        override fun bar2() = println("Implementing object expression 2")
    }
   
    listener.bar()
}

/* This is a singleton */
object Payroll {
    val allEmployees = ArrayList<Person>()

    fun calculateSalary() {
        for(person in allEmployees){
        }
    }
}

/* Use object when you need to implement an interface but there is no need to store state */
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

/* In this case, there will be a single instance of NameComparator associated to Person */
data class Person(val name: String, val address: String, val salary: Double) {
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int = p1.name.compareTo(p2.name)
    }
}

/* Factory pattern */
class User2 private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = User2(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = User2(getFacebookName(accountId))
    }
}

/* Companion objects can be extended */
fun User2.Companion.foo(msg: String) = println(msg)

/* Companion objects can be named, implement interfaces */
interface SomeInterface<T> { fun bar() }
interface AnotherInterface<T> { fun bar2() }

class A {
    companion object SomeCompanionObject: SomeInterface<A> {
        override fun bar() = println("Companion object called")
        fun foo() = println("Foo inside companion object")
    }
}

fun <T> runSomeInterface(someInterface: SomeInterface<T>){
    someInterface.bar()
}
