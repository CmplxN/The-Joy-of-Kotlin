package com.chapter_1.example

import kotlin.test.assertEquals

/*
too hard to conduct tests. (mock card or access to bank)
dependent to the result of charge
 */

class CreditCard{
    fun charge(amount: Int){

    }
}

class Donut{
    companion object{
        val price = 500
    }
}

fun buyDonutBad(creditCard : CreditCard): Donut{
    val donut = Donut()
    creditCard.charge(Donut.price) // side-effect
    return donut // return donut
}

//-----------------------------------------------------------------//

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
    }
}

class Purchase1(val donut : Donut, val payment: Payment)

fun buyDonut1(creditCard: CreditCard): Purchase1{
    val donut = Donut()
    val payment = Payment(creditCard, Donut.price)
    return Purchase1(donut,payment)
}

//-----------------------------------------------------------------//

class Purchase(val donuts: List<Donut>, val payment: Payment)
fun buyDonuts(quantity: Int = 1, creditCard: CreditCard) : Purchase =
        Purchase(List(quantity){ Donut() }, Payment(creditCard, Donut.price * quantity))

//-----------------------------------------------------------------//
//example test code

annotation class Test

class DonutShopKtTest{
    @Test
    fun testBuyDonuts(){
        val creditCard = CreditCard()
        val purchase = buyDonuts(5, creditCard)
        assertEquals(Donut.price * 5, purchase.payment.amount)
        assertEquals(creditCard, purchase.payment.creditCard)
    }
}
