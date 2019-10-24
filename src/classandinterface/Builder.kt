package classandinterface

fun main() {

    /*
    DSL style coding is realized by lambda with receiver.
    This is a kind of builder pattern.
     */
    val somePerson = person {
        //This block is lambda that is used as the extension function of SomePerson.
        firstName = "Takahiro"
        lastName = "sato"
    }
    println(somePerson)

    val sum: Int.(Int) -> Int = {other -> this.plus(other)}
    println(32.sum(11))

    val join: String.(Int) -> String = {other -> this.plus(other.toString())}
    println("hoho".join(123))
}

data class SomePerson(
    var firstName: String? = null,
    var lastName: String? = null
)

fun person(init: SomePerson.() -> Unit): SomePerson {
    val person = SomePerson() //instantiate internally
    person.init() // and build
    return person
}
