import java.lang.Exception

fun main() {
    println(mix(Color.BLUE, Color.YELLOW))
//    println(mix(Color.BLUE, Color.RED)) //exception

    for(i in 0..100) {
        print(fizzbuzz(i))
    }
    for(i in 100 downTo 1 step 3) {
        print(fizzbuzz(i))
    }
}


//example of when takes Set for its argument
fun mix(c1: Color, c2: Color)  =
    when(setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("dirty color.")
    }

enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun fizzbuzz(i: Int) = when {
    i % 15 == 0 -> "FIZZBUZZ "
    i % 3 == 0 -> "FIZZ "
    i % 5 == 0 -> "BUZZ "
    else -> "$i "
}

