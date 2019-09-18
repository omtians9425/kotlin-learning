import java.lang.Exception

fun main() {
    println(mix(Color.BLUE, Color.YELLOW))
//    println(mix(Color.BLUE, Color.RED)) //exception

    for(i in 0..100) {
        print(fizzBuzz(i))
    }
    for(i in 100 downTo 1 step 3) {
        print(fizzBuzz(i))
    }
    println()

    val fullPath = "/Users/hoge/IdeaProjects/kotlin-learning/src/basics.kt"
    parseAndPrintFileName(fullPath)
}


/*
example of when takes Set for its argument.
judges equality of combination by using set
 */
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

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FIZZBUZZ "
    i % 3 == 0 -> "FIZZ "
    i % 5 == 0 -> "BUZZ "
    else -> "$i "
}

fun parseAndPrintFileName(path: String) {
    val directory = path.substringBeforeLast('/')
    val fullName = path.substringAfterLast('/')

    val fileName = fullName.substringBeforeLast('.')
    val extension = fullName.substringAfterLast('.')

    println("directory: $directory, fullName: $fullName, file name: $fileName, extension: $extension")
}
