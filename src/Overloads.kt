fun main() {
    //operators
    println('x' * 10)
    val myCollection = MyCollection(innerList = mutableListOf(1,2,3))
    myCollection += 1
    println(myCollection.joinToString())

    //compareTo
    val p1 = Person(lastName = "Ken", firstName = "Block")
    val p2 = Person(lastName = "Takashi", firstName = "Sato")

    println(p1 > p2)
}

//return value differs from operand's type
operator fun Char.times(count: Int) : String{
    return this.toString().repeat(count)
}

class MyCollection<T> (private val innerList: MutableCollection<T> = mutableListOf()): MutableCollection<T> by innerList

operator fun <T> MyCollection<T>.plusAssign(element: T) {
    this.add(element)
}

//compareTo
class Person(
    private val firstName: String, private val lastName: String
) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        //evaluate specified properties in order
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}


