fun main() {
    println(getBit(1, 0))
    println(setBit(4, 1))
    println(clearBit(2, 1))
    println(clearBitsMsbTo0th(13, 3)) //13; 1101
    println(clearBits0thToIth(13, 2))
    println(updateBit(13, 2, false))
    println(insertBits(107, 7, 1, 4))
    println(inverseBit(13, 2))
    println(inverseBit(13, 1))
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

fun updateBit(num: Int, i: Int, bitIs1: Boolean): Int {
    val value = if(bitIs1) 1 else 0
    val mask = (1 shl i).inv()
    return (mask and num) or (value shl i)
}

fun inverseBit(num: Int, i: Int): Int {
    return num xor (1 shl i)
}

//insert m's full bits to n (range i ~ j th)
fun insertBits(n: Int, m: Int, i: Int, j: Int): Int {
    val left = -1 shl (j + 1)
    val right = 1 shl i - 1
    val clearMask = left or right //mask set 0 for n's i ~j th bits
    return (n and clearMask) or (m shl i)
}