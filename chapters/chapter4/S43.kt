package chapters.chapter4

fun main(args: Array<String>) {
    val bob = Client("Bob", 973293)
    println(bob)
    println(bob.copy(postalCode = 382555))
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
    fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client(name, postalCode)
}
*/
/* The class above is equivalent to */
data class Client(val name: String, val postalCode: Int)

/* Delegates the Collection implementation to theInnerList */
class DelegatingCollection<T>(theInnerList: Collection<T> = ArrayList<T>()) : Collection<T> by theInnerList

/* Delegates the MutableCollection implementation to innerSet */
class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
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
