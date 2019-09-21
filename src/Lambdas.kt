import java.lang.StringBuilder

fun main() {
    var list = mutableListOf(1,2,3,4,5)
    println(list.maxBy { it })
    list = list.map { 2 }.toMutableList()
    print(list.joinToString())

    val listOfList = listOf(
        listOf(1,2,3),
        listOf(3,4,5),
        listOf(5,6,7)
    )
    println(listOfList.flatten().toSet())

    val result = list.customApply {
        addAll(listOf(100, 101, 102))
    }
    println(result.joinToString())
}

//lambda with reveiver
inline fun <T> T.customApply(block: T.() -> Unit): T {
    block()
    return this
}
