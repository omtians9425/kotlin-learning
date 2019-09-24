fun main() {
    var str: String? = null
    print(str.isNullOrBlank())
}

fun String?.isNullOrBlank(): Boolean =
    this == null || this.isBlank() //smart cast