fun main () {
    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser1("test@kotlinlang.org").nickname)
    println(SubscribingUser2("test@kotlinlang.org").nickname)
}

//interface property
interface User {
    val nickname: String //not able to have state; backing field
}

class PrivateUser(override val nickname: String): User //assigned at once at initialization

class SubscribingUser1(private val email: String): User {
    override val nickname: String
        get() = email.substringBefore('@') //calculated for every access
}

class SubscribingUser2(private val email: String): User {
    override val nickname = email.substringBefore('@') //calculated at once at initialization
}

