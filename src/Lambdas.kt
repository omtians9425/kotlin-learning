import java.lang.StringBuilder

fun main() {
    var list = mutableListOf(1, 2, 3, 4, 5)
    println(list.maxBy { it })
    list = list.map { 2 }.toMutableList()
    print(list.joinToString())

    val listOfList = listOf(
        listOf(1, 2, 3),
        listOf(3, 4, 5),
        listOf(5, 6, 7)
    )
    println(listOfList.flatten().toSet())

    println(
        list.myApply {
            addAll(listOf(100, 101, 102))
        }.joinToString()
    )

    println(
        StringBuilder().apply {
            for (c in 'A'..'Z') {
                append(c)
            }
        }.toString()
    )

    println(
        list.myAlso {
            it.addAll(listOf(103,105, 106))
        }.joinToString()
    )

    val withResult = myWith(StringBuilder()) {
        for (c in 'A'..'G') {
            append(c)
        }
        toString()
    }
    println(withResult)

    println(withResult.any { it == 'E' })

    val withResult2 = myAnotherWith(StringBuilder()) {
        for (c in 'A'..'G') {
            it.append(c)
        }
        it.toString()
    }
    println(withResult2)

    println(
        buildString {
            append("from Z to A: ")
            for (c in 'Z' downTo 'A') {
                append(c)
            }
        }
    )


    println(
        StringBuilder().myLet2 {
            append("add")
            toString()
        }
    )

    println(
        StringBuilder().myLet1 {
            it.append("hoge")
            it.toString()
        }
    )

    log { print(this) }
}

/*
lambda with receiver sample
not need to pass target object(; receiver) as an argument because of extension function.
this make it possible to express "operation on the target" with a single argument(lambda).
*/
inline fun <T> T.myApply(block: T.() -> Unit): T {
    this.block() //pass lambda to receiver
    return this
}

//normal lambda version of apply is also
inline fun <T> T.myAlso(block: (T) -> Unit): T {
    block(this) //pass target object to lambda
    return this
}

//argument is extension func, so receiver is referenced by the this keyword in lambda
inline fun <T, R> myWith(target: T, block: T.() -> R): R {
    return target.block() //pass lambda to receiver
}

/*
this version doesn't uses ext, so referenced by the it keyword
also normal lambda version(not lambda with receiver)
 */
inline fun <T, R> myAnotherWith(target: T, block: (T) -> R): R {
    return block(target)
}

inline fun <T, R> T.myLet1(block: (T) -> R): R {
    return block(this)
}

//lambda with receiver
inline fun <T, R> T.myLet2(block: T.() -> R): R {
    return this.block() //pass lambda to receiver
}

fun log(log: String.() -> Unit) {
    "hoge".log()
    "fuga".log()
}