package com.chapter_4.exercise

import java.math.BigInteger

/*
    피보나치 수열의 첫 n항을 표현하는 문자열을 반환하는 피보나치함수를
    여태까지 만든 fold iterate unfold map 등을 통해 구현해라.
 */

fun fibMap(n: Int): String{
    val temp = iterate(Pair(BigInteger.ZERO,BigInteger.ONE),{Pair(it.second,it.second+it.first)},n+1)
    return Map(temp){it.first}.joinToString(", ")
}

fun main(){
    println(fibMap(10))
}
