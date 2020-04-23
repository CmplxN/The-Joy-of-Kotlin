package com.chapter_4.exercise

import java.math.BigInteger

/*
    꼬리 재귀 버전의 피보나치 함수를 만들어라.
 */

fun fibonacci(n:BigInteger): BigInteger{
    tailrec fun fibonacci_(a: BigInteger, b: BigInteger, cnt: BigInteger): BigInteger =
            when (cnt) {
                BigInteger.ONE -> BigInteger.ONE
                BigInteger.TWO -> a + b
                else -> fibonacci_(b, a+b, cnt - BigInteger.ONE)
            }
    return fibonacci_(BigInteger.ZERO, BigInteger.ONE, n)
}

fun main(){
    repeat(10){
        println(fibonacci(BigInteger((1+it).toString())))
    }
    println(fibonacci(BigInteger("11111")))
}