import java.lang.StringBuilder

fun main() {
    println("hogehogefugafuga".myFilter { it == 'a' } )
   println(listOf("hogehoge", "fugafuga").myJoinToString())
}

/**
 * High-order functions define how to use predicate lambda and don't know the content of the predicate
 */
fun String.myFilter(predicate: (Char) -> Boolean) : String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val elem: Char = get(index)
        if(predicate(elem)) {
            sb.append(elem)
        }
    }
    return sb.toString()
}

fun <T> Collection<T>.myJoinToString(
    separator: String = ", ",
    prefix: String = "",
    suffix: String = "",
    transform: ((T) -> String)? = null
) : String {
    val result = StringBuilder(prefix)
    for ((index, elem) in this.withIndex()) {
        if(index > 0) result.append(separator)
        val str = transform?.invoke(elem) ?: elem.toString()
        result.append(str)
    }
    result.append(suffix)
    return result.toString()
}