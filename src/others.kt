fun main() {
    val l1 = Node(1)
    l1.next = Node(2)

    val l2 = Node(4)
    l2.next = Node(8)

    var sum = addNumber(l1, l2)
    //21 + 84 = 105, so head node is 5
    while (sum!=null) {
        println(sum.value)
        sum = sum.next
    }
}

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
    if(carry != 0) {
        val n = Node(carry)
        curr.next = n
    }
    return dummy.next
}