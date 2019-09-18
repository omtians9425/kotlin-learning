import java.lang.StringBuilder


fun main() {
    val list = listOf(3,4,5,6,1,4)
    println(list.joinToString(prefix = "[", postfix = "]"))

    val strList = listOf("a", "aweg", "hoge")
    println(strList.joinToString(prefix = "{", postfix = "}"))

    println(3 multiply 5)
}

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val sb = StringBuilder(prefix)
    for((index, item) in this.withIndex()) {
        sb.append(item)
        if(index < size - 1) {
            sb.append(separator)
        }
    }
    sb.append(postfix)
    return sb.toString()
}

//infix call for integer multiplication
infix fun Int.multiply(multiplier: Int) =
    this * multiplier

