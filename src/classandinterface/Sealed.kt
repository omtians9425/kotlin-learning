package classandinterface

import classandinterface.Expr.*

fun main() {
    println(eval(Sum(Sum(Num(5), Num(4)), Num(1))))
    println(eval(Mul(Sum(Num(5), Num(4)), Num(2))))
}

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
    class Mul(val left: Expr, val right: Expr) : Expr()
}

//no need to write else
fun eval(e: Expr): Int =
    when(e) {
        is Num -> e.value //smart cast
        is Sum -> eval(e.left) + eval(e.right)
        is Mul -> eval(e.left) * eval(e.right)
    }