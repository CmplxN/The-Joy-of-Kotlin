package com.chapter_2.example

/*
    참조 동등성(referential equality) : === and !==
    구조 동등성(structural equality) : == and !=
 */

class Test1(val a: Int, val b: Int)

fun main(){
    val a = Pair(1,1)
    val b = Pair(1,1)
    println(a === b)
    println(a == b)

    val troll = 1
    val c: Int = 2147483647
    val d: Int = 2147483646 + troll
    println(c === d)
    println(c == d)

    // == === 연산자가 적용될리 없는 일반 class는?

    val A = Test1(1,1)
    val B = Test1(1,1)
    println(A==B)
    println(A===B)
}