package bitoperations

import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    println(getBit(1, 0))
    println(setBit(4, 1))
    println(clearBit(2, 1))
    println(clearBitsMsbTo0th(13, 3)) //1101
    println(clearBits0thToIth(13, 2))
}

//get bit on i th index of num
fun getBit(num: Int, i: Int): Boolean {
    return ((1 shl i) and num) != 0
}

//set bit "1" on i th index of num
fun setBit(num: Int, i: Int): Int {
    return ((1 shl i) or num)
}

//clear i th bit
fun clearBit(num: Int, i: Int): Int{
    return (1 shl i).inv() and num
}

fun clearBitsMsbTo0th(num: Int, i: Int): Int {
    val mask = (1 shl i) - 1
    return mask and num
}

fun clearBits0thToIth(num: Int, i: Int): Int {
    val mask = (-1 shl (i+1))
    return mask and num
}