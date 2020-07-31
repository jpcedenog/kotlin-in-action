package chapters.chapter3

import utils.strings.myRepeat
import utils.strings.lastChar as last

fun main(args: Array<String>) {
    println("Juan Pablo".last())
    println("Juan Pablo".last)
    println("JP ".myRepeat(5))

    val view: View = Button()
    view.click()
    view.showOff() /* Extension functions cannot be overriden */
}

open class View {
    open fun click() = println("View clicked!")
}

class Button : View() {
    override fun click() = println("Button clicked!")
}

/* Extension functions cannot be overridden, they depend on
the declared static type of the variable not on the runtime type */
fun View.showOff() = println("I'm a View!")
fun Button.showOff() = println("I'm a Button!")
