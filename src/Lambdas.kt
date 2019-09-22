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

    val withResult = myWith(StringBuilder()) {
        for (c in 'A'..'G') {
            append(c)
        }
        toString()
    }
    println(withResult)

    println(withResult.any { it == 'E' })
}

/*
lambda with receiver sample
not need to pass target object(; receiver) as an argument because of extension function.
this make it possible to express "operation on the target" with a single argument(lambda).
*/
inline fun <T> T.myApply(block: T.() -> Unit): T {
    block()
    return this
}

//argument is extension func, so receiver is referenced by the this keyword in lambda
inline fun <T, R> myWith(target: T, block: T.() -> R): R {
    return target.block()
}

//this version doesn't uses ext, so referenced by the it keyword
inline fun <T, R> myAnotherWith(target: T, block: (T) -> R): R {
    return block(target)
}