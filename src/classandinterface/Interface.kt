package classandinterface

fun main () {
    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser1("test@kotlinlang.org").nickname)
    println(SubscribingUser2("test@kotlinlang.org").nickname)

    val collection1 = DelegationCollection(innerList = mutableListOf("hoge", "huga", "piyo"))
    println("size: ${collection1.size}, isEmpty: ${collection1.isEmpty()}, contains(hoge): ${collection1.contains("hoge")}")
    collection1.forEach { println(it) }

    val collection2 = DelegationCollection<String>()
    println(collection2.isEmpty())

    val mutableCollection = DelegationMutableCollection<Int>()
    mutableCollection.addAll(listOf(1,2,3))
    mutableCollection.forEach{ println(it)}

    val set = CountingSet(mutableSetOf(1, 2, 3, 3, 3, 3, 3, 3))
    for (i in 1..10) {
        set.add(i)
    }
    for (i in 1..10) {
        set.add(i)
    }
    println("set")
    set.forEach {
        println(it)
    }
    println("added for set: ${set.objAdded}") //20

}

//interface property
interface User {
    val nickname: String //not able to have state; backing field
}

/**
 * delegation for interface's implementation
 */
class PrivateUser(override val nickname: String): User //assigned at once at initialization

class SubscribingUser1(private val email: String): User {
    override val nickname: String
        get() = email.substringBefore('@') //calculated for every access
}

class SubscribingUser2(private val email: String): User {
    override val nickname = email.substringBefore('@') //calculated at once at initialization
}

//delegation on my own
class SelfDelegationCollection<T> : Collection<T> {
    private val innerList = arrayListOf<T>()

    override val size: Int get() = innerList.size
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun iterator(): Iterator<T> = innerList.iterator()
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
    override fun isEmpty(): Boolean = innerList.isEmpty()
}

//delegation by by keyword
class DelegationCollection<T> (innerList: Collection<T> = listOf()) : Collection<T> by innerList
class DelegationMutableCollection<T>(innerList: MutableCollection<T> = mutableListOf()) : MutableCollection<T> by innerList

//override only the methods you want to customize and delegate others
class CountingSet<T>(private val innerSet: MutableSet<T> = HashSet()) : MutableCollection<T> by innerSet {
    var objAdded = 0

    override fun add(element: T): Boolean {
        objAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objAdded += elements.size
        return innerSet.addAll(elements)
    }
}