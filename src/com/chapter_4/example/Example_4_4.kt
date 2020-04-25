package com.chapter_4.example

import java.math.BigInteger

/*
    메모화 : 계산 결과를 메모리에 저장해서 나중에 같은 계산을 할 때 결과를 바로 돌려주는 방식
    이는 변화하는 상태를 저장하는 것인데, 결과를 저장하는 효과는 함수 밖에서 관찰할 수 없다??
 */

fun main(){
    println(fibo(10))
}

fun fibo(limit: Int): String=
    when{
        limit < 1 -> throw IllegalArgumentException()
        limit == 1 -> "1"
        else-> {
            var fibo1 = BigInteger.ONE
            var fibo2 = BigInteger.ONE
            var fibonacci: BigInteger
            val builder = StringBuilder("1, 1")
            for (i in 2 until limit) {
                fibonacci = fibo1.add(fibo2)
                builder.append(", ").append(fibonacci)
                fibo1 = fibo2
                fibo2 = fibonacci
            }
            builder.toString()
        }
    }

// 전 튜플로 다음 튜플을 만드는 식으로 메모화를 할 수도 있다.
val f = {
    (a,b): Pair<BigInteger,BigInteger> ->
    Pair(b, a+b)
}