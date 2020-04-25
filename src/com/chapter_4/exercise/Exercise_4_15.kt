package com.chapter_4.exercise

import java.math.BigInteger

/*
    정수 n을 인자로 받아서 피보나치 함수의 0항부터 n항 까지
    모든 항을 나열하는 문자열을 반환하는 함수를 작성하라.(꼬리재귀)
 */

fun fibM(n: Int): String {
    tailrec fun fibM_(rtn: List<BigInteger>, a: BigInteger, b: BigInteger, m: BigInteger): List<BigInteger> =
            when (m) {
                BigInteger.ZERO -> rtn
                BigInteger.ONE -> rtn + (a + b)
                else -> fibM_(rtn + (a + b), b, a + b, m - BigInteger.ONE)
            }

    val answer = fibM_(listOf(BigInteger.ZERO),
            BigInteger.ONE, BigInteger.ZERO, BigInteger((n).toString()))
    return answer.joinToString(", ")
}

fun main(){
    println(fibM(5))
}