package chapters.chapter4

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

class subscribingUser(override val email: String): MyUser {
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
