package chapters.chapter4

import java.io.File

fun main(args: Array<String>){
    utils.printTitle(4)

    val button = Button()
    button.click()
    button.setFocus(true)
    button.showOff()
    println(eval(Expr.Sum(Expr.Sum(Expr.Num(1), Expr.Num(2)), Expr.Num(4))))

    /* This is default constructor created by the compiler */
    val classWithDefaultValues = ClassWithDefaultValues() 
    println("${classWithDefaultValues.first} ${classWithDefaultValues.last}")

    val userFoo = UserFoo("JP Cedeno")
    userFoo.address = "1709 Vinings Pkwy"
    println(userFoo.address)
    userFoo.address = "1709 Vinings Pkwy"
    println(userFoo.address)

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Mr JP")
    //lengthCounter.counter++ /* Illegal. Setter is private in LengthCounter */
    println(lengthCounter.counter)

    val bob = Client("Bob", 973293)
    println(bob)
    println(bob.copy(postalCode=382555))

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
    
    listener.bar()
}

interface Clickable {
    fun click()
    fun showOff() = println("I am clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun click() = println("I was clicked!")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

open class RichButton : Clickable { /* Class can be inherited */
    fun disable() {}                /* Method cannot be overriden */
    open fun animate() {}           /* Method can be overriden */
    final override fun click() {}   /* Method cannot be overriden because of the 'final' keyword */
}

abstract class Animated {
    abstract fun animate()          /* Must be overriden */
    open fun stopAnimating(){ }     /* Can be optionally overriden */
    fun animateTwice(){ }           /* Cannot be overriden */
}

class Animatedly: Animated(){
    override fun animate(){ }
    override fun stopAnimating(){ }
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

/* **** This class breaks all rules
fun TalkativeButton.giveSpeech(){
    yell()
    whisper()
}
*/

class Outer {
    inner class Inner { /* Inner classes have to be declared explicitly. Otherwise, they are considered nested by default */
        fun getOuterReference() : Outer = this@Outer
    }
}

sealed class Expr {
    class Num(val value: Int): Expr()
    class Sum(val left: Expr, val right: Expr): Expr()
}


fun eval(e: Expr): Int = when(e) { /* the "when" expression covers all possible cases, no "else" branch is needed */
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.right) + eval(e.left)
}

/* If all constructor parameters have default values, the compiler generates 
an additional constructor without parameters that uses all the default values */
class ClassWithDefaultValues(val first: String = "JP", val last: String = "Cedeno")

/* A default constructor without parameters will be generated automatically */
open class foo

/* This class cannot be instantiated by code outside the class */
class Secretive private constructor()

open class FooView {
    constructor(id : Int) {
        println(id)
    }
    constructor(id : Int, label : String){
        println(id)
        println(label)
    }
}

class MyButton : FooView {
    val id : Int
    val label : String

    constructor(id : Int) : this(id, "DEFAULT")

    constructor(id : Int, label : String) : super(id, label) {
        this.id = id
        this.label = label
    }
}

interface MyUser {
    val nickName: String /* abstract property */
    val email: String /* abstract property */
    val anotherNickName : String get() = email.substringBefore('@') /* property with getter. Backing fields are not allowed in interfaces */
}

class PrivateUser(override val nickName: String, override val email: String): MyUser

class SubscribingUser(override val email: String): MyUser {
    override val nickName: String get() = email.substringBefore('@') /* substringBefore is calculated in every access */
    override val anotherNickName: String get() = email.substringBefore('@') /* substringBefore is calculated in every access */
}

class FacebookUser(val accountId: Int, override val email: String): MyUser {
    override val nickName = getFacebookName(accountId) /* Backing field stores the data computed during class initialization */
}

private fun getFacebookName(accountId: Int): String {
    return accountId.toString()
}

class UserFoo(val name: String){
    var address: String = "unspecified"
        set(value: String){
            println("""Address was changed for $name "$field" -> "$value".""".trimIndent())
            field = value
        }
        get() = """The value is $field"""
}

class LengthCounter {
    var counter = 0
        private set

    fun addWord(word: String){
        counter += word.length
    }
}

/*
class Client(val name: String, val postalCode: Int){
    override fun equals(other: Any?): Boolean{
        if(other !is Client)
            return false
        else return name == other.name && postalCode == other.postalCode
    }
    override fun toString() = "Client(name = $name, postalCode = $postalCode)"
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}
*/
/* The class above is equivalent to */
data class Client(val name: String, val postalCode: Int)

/* Delegates the MutableCollection implementation to innerSet */
class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()): MutableCollection<T> by innerSet {
    var objectsAdded = 0

    /* It does not delegate, however, the implementation of these two methods */
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

/* In this case, there will be a single instance of NameComparator associated to Person */
data class Person(val name: String, val address: String, val salary: Double) {
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int = p1.name.compareTo(p2.name)
    }
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

/* Factory pattern */
class User2 private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = User2(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = User2(getFacebookName(accountId))
    }
}

/* Companion objects can be extended */
fun User2.Companion.foo(msg: String) = println(msg)

/* Object expression */
val listener: SomeInterface<String> = object: SomeInterface<String>, AnotherInterface<String> {
    override fun bar() = println("Implementing object expression 1")
    override fun bar2() = println("Implementing object expression 2")
}
