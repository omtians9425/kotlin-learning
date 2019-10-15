import java.lang.IllegalArgumentException

fun main() {
    val list1: List<String> = listOf("a", "b")
    typeCheck(list1)
    typeCheck(1)

    printSum(listOf(1,2,3))
    printSum(listOf(1.0,2.0,3.0))
    printSum(setOf(1,2,3)) //Illegal Argument Exception
//    printSum(listOf("1","2","3")) //ClassCastException occurs at sum
}

fun <T> typeCheck(list: T) {
    //type erasure; the following cannot be compiled
//    if(list is List<String>) {
//
//    }

    //this can be compiled
    if(list is List<*>) {
        println("this is list.")
    } else {
        println("this is not list")
    }
}

fun printSum(c: Collection<*>) {
    /*
    By type erasure, "as" cast only check whether list or not but we can treat as integer.
    Therefore, even if it is List<String>, the cast succeeds, but if it is treated as integer, an exception occur.
     */
    val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}



