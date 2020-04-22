package com.chapter_3.example

/*
    암시적 인자는 문제적 요소다.
    ==> closure 대신 튜플을 인자로 받는 함수 사용
    커리한 함수 : 인자가 하나뿐이며 인자가 하나뿐인 다른 함수를 반환하는 함수.
    # 커리한 함수는 튜플과 달리 부분적용이 가능하다!! (부분 적용 함수)
    # 항등함수를 정의할 수도 있다. val identity: ~~ = {it}
 */

val taxRate = 0.09
//fun addTax(price: Double) = price + price * taxRate // 외부요인인 taxRate. 위험

//val addTax = {taxRate: Double, price: Double -> price + price * taxRate}
val addTaX = {taxRate: Double ->
    {price: Double ->
        taxRate * price + price
    }
}

class TaxComputer(private val rate: Double){
    fun compute(price: Double) = price + price * rate
}

// 객체지향 프로그래밍
val tc9 = TaxComputer(0.09)
val price = tc9.compute(12.0)

// 함수형 프로그래밍
val tc10 = addTaX(0.1)
val pricE = tc10(12.0)

// 항등함수
val identity: (Any) -> Any = {it}

fun main() = println(addTaX(taxRate)(12.12))