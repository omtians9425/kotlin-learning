import java.util.*

fun main() {
    val charAndBinaryMap = createCharAndBinaryMap()
    for((key, value) in charAndBinaryMap) {
        println("$key == $value")
    }
}

//key: alphabetic character, value: its ascii code value
private fun createCharAndBinaryMap(): TreeMap<Char, String> {
    val charAndBinaryMap = TreeMap<Char, String>()

    for(c in 'A'..'I') {
        val binaryString = Integer.toBinaryString(c.toInt())
        charAndBinaryMap[c] = binaryString
    }
    return charAndBinaryMap
}