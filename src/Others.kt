import classandinterface.joinToString
import kotlin.system.measureNanoTime

fun main() {
    val l1 = Node(1)
    l1.next = Node(2)

    val l2 = Node(4)
    l2.next = Node(8)

    var sum = addNumber(l1, l2)
    //21 + 84 = 105, so head node is 5
    while (sum != null) {
        println(sum.value)
        sum = sum.next
    }

    println(getPerms("abcd")?.joinToString())
    println(getPerms("aaaaaaaaaa")?.joinToString())
    println(getPerms("aaaaaaaaaabbbbbbbccccccccccccc")?.joinToString())
    println(getPerms("")?.joinToString())
    println(getPerms(null)?.joinToString())

    println("get all parentheses for 1: ${getParen(1)?.joinToString()}")
    println("get all parentheses for 2: ${getParen(2)?.joinToString()}")
    println("get all parentheses for 3: ${getParen(3)?.joinToString()}")
    println("get all parentheses for 4: ${getParen(4)?.joinToString()}")

    val image: Array<Array<Colors>> = Array(10) { Array(10) { Colors.R } }
    initImage(image)
    printImage(image)
    paint(image, 0, 4, Colors.G)
    printImage(image)

    println("coin change: ${makeCoinChange(15)}" )
    println("coin change: ${makeCoinChangeMemo(15)}" )

    println(measureNanoTime { makeCoinChange(1000) })
    println(measureNanoTime { makeCoinChangeMemo(1000) })
}

/*
add numbers as two linked lists
 */
//linked list
class Node(var value: Int) {
    var next: Node? = null
}

fun addNumber(n1: Node?, n2: Node?): Node? {
    var curr = Node(0)
    val dummy = curr
    var carry = 0
    var l1 = n1
    var l2 = n2
    while (l1 != null || l2 != null) {
        val v1 = l1?.value ?: 0
        val v2 = l2?.value ?: 0
        val sum = v1 + v2 + carry
        val num = sum % 10
        carry = sum / 10
        val n = Node(num)
        curr.next = n
        curr = curr.next!!
        l1 = l1?.next
        l2 = l2?.next
    }
    if (carry != 0) {
        val n = Node(carry)
        curr.next = n
    }
    return dummy.next
}

/*
List all permutations.
Implemented by dp (top down)
 */

fun getPerms(s: String?): List<String>? {
    fun insertForAllPos(word: String, c: Char): List<String> {
        val ret = mutableListOf<String>()
        for (i in 0..word.length) { //not word.indices
            val begin = word.substring(0, i)
            val end = word.substring(i)
            ret.add(begin + c + end)
        }
        return ret
    }
    if (s == null) return null
    if (s.isEmpty() || s.length == 1) {
        return listOf(s)
    }
    val ret = mutableListOf<String>()
    val first: Char = s[0]
    val remaining = s.substring(1)
    val remainPerms = getPerms(remaining)
    return remainPerms?.let {
        println("remaining: ${it.joinToString()}")
        //omit duplicates
        if (it.any { elem -> elem.contains(first) }) {
            return it //non-local return
        }
        for (word in it) {
            val words = insertForAllPos(word, first)
            println("insert new char[$first] for[$word] in remaining: ${words.joinToString()}")
            ret.addAll(words)
        }
        ret
    }
}


/*
List all valid parentheses
 */
fun getParen(n: Int): List<String>? {
    require(n >= 0) { "argument n must be greater than 0" }
    if (n == 0) return null
    if (n == 1) return listOf("()")
    val shorter = getParen(n - 1) ?: return null
    val newer = mutableListOf<String>()
    for ((index, elem) in shorter.withIndex()) {
        if (index == 0) {
            newer.add("$elem()")
        } else {
            newer.add("()$elem")
            newer.add("$elem()")
        }
        newer.add("($elem)")
    }
    return newer
}


/*
Fill an area with the same color by specified color
 */
enum class Colors { R, G, B }

fun paint(mat: Array<Array<Colors>>, r: Int, c: Int, nColor: Colors): Boolean {
    if (nColor == mat[r][c]) return false
    return helper(mat, r, c, mat[r][c], nColor)
}

fun helper(mat: Array<Array<Colors>>, r: Int, c: Int, oColor: Colors, nColor: Colors): Boolean {
    if (r < 0 || r >= mat.size || c < 0 || c >= mat[0].size) {
        return false
    }
    if (mat[r][c] == oColor) {
        mat[r][c] = nColor
        helper(mat, r + 1, c, oColor, nColor)
        helper(mat, r, c + 1, oColor, nColor)
        helper(mat, r - 1, c, oColor, nColor)
        helper(mat, r, c - 1, oColor, nColor)
        return true
    }
    return false
}

fun printImage(mat: Array<Array<Colors>>) {
    println("########### IMAGE ############")
    for (i in mat.indices) {
        for (j in mat[0].indices) {
            print(mat[i][j].toString())
        }
        println()
    }
    println()
}

fun initImage(mat: Array<Array<Colors>>) {
    for (i in mat.indices) {
        for (j in mat[0].indices) {
            if (i == 0) {
                mat[i][j] = Colors.B
            } else {
                mat[i][j] = Colors.R
            }
        }
        println()
    }
}

fun makeCoinChange(n: Int) : Int {
    val denoms = intArrayOf(25, 10, 5, 1)
    return makeCoinChange(n, denoms, 0)
}

fun makeCoinChange(n: Int, denoms: IntArray, index: Int): Int {
    if(n <= 0 || index >= denoms.size - 1) return 1;
    val denomAmount = denoms[index]
    var ret = 0
    var count = 0
    while (denomAmount * count <= n) {
        val remaining = n - denomAmount * count
        ret += makeCoinChange(remaining, denoms, index + 1)
        count++
    }
    return ret
}

fun makeCoinChangeMemo(n: Int) : Int {
    val denoms = intArrayOf(25, 10, 5, 1)
    val memo = Array(n + 1) {IntArray(denoms.size)}
    return makeCoinChangeMemo(n, denoms, 0, memo)
}

fun makeCoinChangeMemo(n: Int, denoms: IntArray, index: Int, memo: Array<IntArray>): Int {
    if(n <= 0 || index >= denoms.size - 1) return 1;
    val denomAmount = denoms[index]
    var ret = 0
    var count = 0
    while (denomAmount * count <= n) {
        val remaining = n - denomAmount * count
        if(memo[remaining][index] != 0) {
            ret += memo[remaining][index]
        } else {
            memo[remaining][index] =  makeCoinChangeMemo(remaining, denoms, index + 1, memo)
            ret += memo[remaining][index]
        }
        count++
    }
    return ret
}


