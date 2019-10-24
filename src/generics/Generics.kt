package generics

import java.lang.IllegalArgumentException
import java.util.*

fun main() {
    val list1: List<String> = listOf("a", "b")
    typeCheck(list1)
    typeCheck(1)

    printSum(listOf(1, 2, 3))
    printSum(listOf(1.0, 2.0, 3.0))
//    generics.printSum(setOf(1,2,3)) //Illegal Argument Exception
//    generics.printSum(listOf("1","2","3")) //ClassCastException occurs at sum

    //reified function sample
    println(listOf(1, 2, "fuga", 3, "hoge").filterInstance<String>())
    val stringLoader = loadService<String>()

}

fun <T> typeCheck(list: T) {
    //type erasure; the following cannot be compiled
//    if(list is List<String>) {
//
//    }

    //this can be compiled
    if (list is List<*>) {
        println("this is list.")
    } else {
        println("this is not list")
    }
}

@Suppress("UNCHECKED_CAST")
fun printSum(c: Collection<*>) {
    /*
    By type erasure, "as" cast only check whether list or not but we can treat as integer.
    Therefore, even if it is List<String>, the cast succeeds, but if it is treated as integer, an exception occur.
     */
    val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}

inline fun <reified T> Iterable<*>.filterInstance(): List<T> {
    val ret = mutableListOf<T>()
    for (element in this) {
        //reified type parameter can be use because this method is embedded in the caller with the type resolution
        if (element is T) {
            ret.add(element)
        }
    }
    return ret
}

inline fun <reified T> loadService(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java) //use reified parameter
}



