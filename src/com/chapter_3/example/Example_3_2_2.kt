package com.chapter_3.example

import com.chapter_1.example.CreditCard

/*
    클래스 인스턴스의 프로퍼티에 접근하는 인스턴스 함수는 해당 클래스의 인스턴스를 암시적 파라미터로 받는다.
    그 말은 자신이 들어있는 클래스의 인스턴스에 접근하지 않을시 그 함수는 패키지 수준으로 빼거나 companion에 넣을 수 있다.

    companion이나 패키지 수준으로 함수를 정의하면 예기치 않은 클래스내 인스턴스 접근으로 인한 버그를 방지할 수 있다.
    하지만 합성함수를 쓸 때 장황해지고 읽기 어려워진다.

    ex) payment1.combine(payment2).combine(payment3)
        vs
        combine(payment1,combine(payment2,payment3))
 */

class Payment(val creditCard: CreditCard, val amount: Int){
    fun combine(payment: Payment): Payment=
            if(creditCard == payment.creditCard)
                Payment(creditCard, amount + payment.amount)
            else
                throw IllegalStateException("Card does not match.")
    companion object{
        fun groupByCard(payments: List<Payment>): List<Payment> =
                payments.groupBy { it.creditCard } // Map<CreditCard, List<Payment>>
                        .values // List<List<Payment>>로 변환
                        .map{ // 각 List<Payment>당
                            it.reduce(Payment::combine) // 하나의 Payment로 합치자.
                        }
        fun combine(payment1: Payment, payment2: Payment): Payment =
                if(payment1.creditCard==payment2.creditCard)
                    Payment(payment1.creditCard, payment1.amount+payment2.amount)
                else
                    throw java.lang.IllegalStateException("Cards don't match")
    }
}