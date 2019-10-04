package designpattern

/**
 * implementation of strategy pattern with lambda.
 * lambda replaces all interface. we only do implement strategy as method
 */
fun main() {

}

fun findBestPromo(order: Order, promos: List<(Order) -> Double>): Sequence<Pair<String, Double>>? {
    return promos.asSequence().map { Pair(it.toString(), it.invoke(order)) }.sortedBy { it.second }
}

data class Customer(val name: String, val fidelityScore: Int)

data class OrderItem(val productName: String, val qtyInKg: Int, val pricePerKg: Double) {
    fun total(): Double {
        return qtyInKg * pricePerKg
    }
}

//promo is strategy
class Order(val customer: Customer, val cart: List<OrderItem>, val promo: ((Order) -> Double)? = null) {
    fun total(): Double = cart.asSequence().sumByDouble { it.total() }

    fun finalPrice(): Double {
        val discount = promo?.invoke(this) ?: 0f.toDouble() //functionN interface has invoke method (SAM)
        return total() - discount
    }

    //-----3 strategies-----
    fun fidelityPromo(order: Order): Double {
        return if (order.customer.fidelityScore > 1000) {
            order.total() * 0.5
        } else {
            0f.toDouble()
        }
    }

    fun bulkPromo(order: Order): Double {
        var discount: Double = 0f.toDouble()

        for (item in order.cart) {
            if (item.qtyInKg >= 20) {
                discount += item.total() * 0.1
            }
        }
        return discount
    }

    fun largePromo(order: Order): Double {
        val uniqueItems = order.cart.toSet().size
        return if (uniqueItems >= 10) {
            order.total() * 0.07
        } else {
            0f.toDouble()
        }
    }
}
