import java.lang.StringBuilder


fun main() {
    val list = listOf(3,4,5,6,1,4)
    println(list.joinToString(prefix = "[", postfix = "]"))
}

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val sb = StringBuilder(prefix)
    for((index, item) in this.withIndex()) {
        sb.append(item).append(separator)
    }
    sb.append(postfix)
    return sb.toString()
}