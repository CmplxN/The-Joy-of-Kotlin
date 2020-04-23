package com.chapter_3.example

data class ProducTN(val name: String, val price: PriceN, val weight: WeightN)

data class OrderLinEN(val product: ProducTN, val count: Int){
    fun weight() = product.weight * count
    fun amount() = product.price * count
}

/*
    # invoke : 이름 없이 쓸 수 있는 함수(정확히는 연산자)
               invoke를 만들면 생성자가 private여도 같은 형식으로 객체를 만들 수 있다.
               차후에 더 알아봐야할 필요 있음.
 */

data class PriceN private constructor(private val value: Double){ // 실제론 copy를 통해서 우회가능
    override fun toString(): String = value.toString()
    operator fun plus(price:PriceN) = PriceN(this.value + price.value)
    operator fun times(num: Int) = PriceN(this.value * num)

    companion object{
        val identity = PriceN(0.0)
        operator fun invoke(value: Double) =
                if(value > 0)
                    PriceN(value)
                else
                    throw IllegalArgumentException("Price must be positive or null")
    }
}
data class WeightN private constructor(private val value: Double){ // 실제론 copy를 통해서 우회가능
    override fun toString(): String = value.toString()
    operator fun plus(weight:WeightN) = WeightN(this.value + weight.value)
    operator fun times(num: Int) = WeightN(this.value * num)

    companion object{
        val identity = WeightN(0.0)
        operator fun invoke(value: Double) =
                if(value > 0)
                    WeightN(value)
                else
                    throw IllegalArgumentException("Weight must be positive or null")
    }
}

///////////////////////////invoke///////////////////////////
/*
    람다는 결국 invoke 함수 한개만 가지는 객체지 않을까?
    그리고 개발자는 invoke를 호출하는데 알아차리지 못할뿐?
 */

val toUpperCasee = { str: String  -> str.toUpperCase() }

fun tUP(str: String) = str.toUpperCase()

val toUpperCasE = object : Function1<String, String> {
    override fun invoke(p1: String): String {
        return p1.toUpperCase()
    }
}

fun main(){
    val toothPaste = ProducTN("Tooth Paste", PriceN(1.5), WeightN(0.5))
    val toothBrush = ProducTN("Tooth Brush", PriceN(3.5), WeightN(0.3))
    val orderLines = listOf(OrderLinEN(toothPaste, 2),
            OrderLinEN(toothBrush, 3))
    val weight = orderLines.fold(WeightN.identity){a,b->a+b.weight()} // sum~ 도 결국 fold의 구체화
    val price = orderLines.fold(PriceN.identity) {a,b->a+b.amount() } // 이제 여기에 weight하면 컴파일에러
    println("Total price: $price")
    println("Total weight: $weight")

    listOf("a","b","c").map(::tUP)
    listOf("a","b","c").map(toUpperCasee)
}
