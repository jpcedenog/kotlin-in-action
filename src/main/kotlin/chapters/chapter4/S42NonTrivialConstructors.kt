package chapters.chapter4

import java.io.File

fun main(args: Array<String>){
    val user = User(nickname="JP", address="1234 SmallTown")
    println("${user.nickname}")

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
}

/* 
'val' means that the corresponding property is generated for the constructor parameter. 'address'
will not be created as a property
*/
open class User(val nickname: String, address: String)
/*
If your class has a superclass, the primary constructor also needs to initialize the superclass.
You can do so by providing the superclass constructor parameters after the
superclass reference in the base class list
*/
class TwitterUser(nickname: String, address: String): User(nickname, address)

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

    constructor(id : Int) : this(id, "DEFAULT") /* This constructor calls another constructor in the same class */

    constructor(id : Int, label : String) : super(id, label) { /* The superclass' constructor needs to be invoked */
        this.id = id
        this.label = label
    }
}

/*
Classes implementing the User interface need to provide a way to obtain the value of nickname and email
*/
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
            println("""Address was changed for $name "$field" -> "$value".""".trimIndent()) /* Reads the backing value */
            field = value /* Updates the backing value */
        }
        get() = """The value is $field"""
}

class LengthCounter {
    var counter = 0 
        private set /* 'counter' cannot be updated outside the class */

    fun addWord(word: String){
        counter += word.length
    }
}
