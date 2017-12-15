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