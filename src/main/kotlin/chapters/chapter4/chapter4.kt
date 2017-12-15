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

open class RichButton : Clickable {
    fun disable() {}
    open fun animate() {}
    final override fun click() {}
}

class foo: RichButton() {

}
