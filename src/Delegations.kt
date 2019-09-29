import kotlin.contracts.CallsInPlace
import kotlin.contracts.contract

/**
 * Delegation for property's accessor.
 * For interface's implementation, see [Interface.kt]
 */
fun main () {

    val p = Person("Ken")
    p.emails.forEach { println(it.text) }
}

/*
about lazy initialization
 */
class Email(val text: String)

fun loadEmails(person: Person): List<Email> {
    println("Load emails for ${person.name}")
    return listOf(Email("hogehoge...for${person.name}"), Email("fugafuga..."))
}

//backing property and one time initialization logic is encapsulated
class Person(val name: String) {
    //lambda is invoked when the property is first accessed
    val emails: List<Email> by lazy { loadEmails(this) }
}

/*
on my own version. basically, above is syntax sugar of this code
In above, backing property and one time initialization logic is encapsulated
 */
//class Person(val name: String) {
//    private var _emails: List<Email>? = null //backing property
//
//    val emails: List<Email>
//    get() {
//        if(_emails == null) {
//            _emails = loadEmails(this)
//        }
//        return _emails!!
//    }
//}
