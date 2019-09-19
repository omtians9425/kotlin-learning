fun main() {
    var list = mutableListOf(1,2,3,4,5)
    println(list.maxBy { it })
    list = list.map { 2 }.toMutableList()
    print(list.joinToString())
}


