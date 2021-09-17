package chapters.chapter4

fun main(args: Array<String>) {
    val button = Button()
    button.click()
    button.setFocus(true)
    button.showOff()
    println(eval(Expr.Sum(Expr.Sum(Expr.Num(1), Expr.Num(2)), Expr.Num(4))))
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

    /* 
    Button MUST provide an implementation of showOff as both interfaces 
    provide a default implementation

    "super" qualified by the supertype name in angle brackets specifies the
    parent whose method you want to call.
    */
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

open class RichButton : Clickable { /* Class can be inherited */
    fun disable() {}                /* Method cannot be overriden */
    open fun animate() {}           /* Method can be overriden */
    final override fun click() {}   /* Method cannot be overriden because of the 'final' keyword */
    /* 'final' above is necessary because without it, click() would be open by default because click()
     is open in Focusable */
}

abstract class Animated {
    abstract fun animate()          /* Abstract, so it must be overriden */
    open fun stopAnimating() {}     /* Non-abtract, however, it can be optionally overriden */
    fun animateTwice() {}           /* Non-abstract, cannot be overriden */
}

class Animatedly : Animated() {
    override fun animate() {}
    override fun stopAnimating() {}
}

/* Kotlin offers a new visibility modifier, internal, which means "visible inside a module." 
A module is a set of Kotlin files compiled together. It may be an IntelliJ IDEA module, an 
Eclipse project, a Maven or Gradle project, or a set of files compiled with an invocation of 
the Ant task. */

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}


/* **** This class breaks all rules
fun TalkativeButton.giveSpeech(){           //Error: "public" member exposes its "internal" receiver type TalkativeButton
    yell()                                  //Error: yell() is private in TalkativeButton
    whisper()                               //Error: whisper() is protected in TalkativeButton
}
*/

class Outer {
    /* Inner classes have to be declared explicitly. Otherwise, they are considered static nested by default */
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}

/*
You mark a superclass with the sealed modifier, and that restricts the possibility of creating subclasses. All
the direct subclasses must be nested in the superclass. Sealed classes cannot have inheritors defined outside the class

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
*/

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int = when (e) { /* the "when" expression covers all possible cases, no "else" branch is needed */
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.right) + eval(e.left)
}
