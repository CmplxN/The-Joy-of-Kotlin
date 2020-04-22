package com.chapter_3.example

/*
    taxRate와 price는 궤를 달리하나 둘 다 Double이다. 더해서는 안되는 타입이다. 어떻게 방지할까?
    일단 클래스 하나에는 타입이 같은 프로퍼티가 여럿 있는 것을 지양한다.
    ==> 새 타입을 활용하여 컴파일러에서 타입 오류를 탐지할 수 있게 작성하자.
 */

data class Product(val name: String, val price: Double, val weight: Double)

data class OrderLine(val product: Product, val count: Int){
    fun weight() = product.weight * count
    fun amount() = product.price * count
}

object Store{ // 싱글턴
    @JvmStatic // 자바에서 이 함수를 정적 메서드처럼 호출할 수 있다. ?? 이 예제 뭐죠?
    fun main(){
        val toothPaste = Product("Tooth Paste", 1.5, 0.5)
        val toothBrush = Product("Tooth Brush", 3.5, 0.3)
        val orderLines = listOf(OrderLine(toothPaste, 2),
        OrderLine(toothBrush, 3))
        val weight = orderLines.sumByDouble { it.amount() } // deliberate error
        val price = orderLines.sumByDouble { it.weight() } // deliberate error
        println("Total price: $price")
        println("Total weight $weight")
    }
}

/////////////////////////////////////개선/////////////////////////////////////////
data class Price(val value: Double){
    override fun toString(): String = value.toString()
    operator fun plus(price:Price) = Price(this.value + price.value)
    operator fun times(num: Int) = Price(this.value * num)
}
data class Weight(val value: Double){
    override fun toString(): String = value.toString()
    operator fun plus(weight:Weight) = Weight(this.value + weight.value)
    operator fun times(num: Int) = Weight(this.value * num)
}

data class ProducT(val name: String, val price: Price, val weight: Weight)

data class OrderLinE(val product: ProducT, val count: Int){
    fun weight() = product.weight * count
    fun amount() = product.price * count
}

fun main(){
    val toothPaste = ProducT("Tooth Paste", Price(1.5), Weight(0.5))
    val toothBrush = ProducT("Tooth Brush", Price(3.5), Weight(0.3))
    val orderLines = listOf(OrderLinE(toothPaste, 2),
            OrderLinE(toothBrush, 3))
    val weight = orderLines.fold(Weight(0.0)){a,b->a+b.weight()} // sum~ 도 결국 fold의 구체화
    val price = orderLines.fold(Price(0.0)) {a,b->a+b.amount() } // 이제 여기에 weight하면 컴파일에러
    println("Total price: $price")
    println("Total weight $weight")
}