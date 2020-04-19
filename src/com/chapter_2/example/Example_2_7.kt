package com.chapter_2.example

/*
    # Block Syntax
    # Expression Syntax
    # 타입지정을 해주면 오류를 미리 발견할 수 있다.
 */

fun add(a:Int, b:Int): Int = a + b
fun addd(a:Int, b:Int): (Int,Int) -> Int = { a: Int, b: Int -> // returning a function
    a + b
}

/*
    isPrime 함수를 sumOfPrimes 밖에서 정의할 수는 없다. isPrime이 seq를 가두어 닫기(close over)때문.
    이런 구조를 closure라고 한다.
    isPrime 함수를 sumOfPrimes 바깥에서 사용할 일이 없다면 isPrime을 sumOfPrimes의 로컬함수로 하는 구조가 좋다.
    이는 자신이 가두어 닫은 변수(seq)를 함수 인자로 받는 것과 비슷하다.
 */

fun sumOfPrimes(limit: Int): Long {
    val seq = sequenceOf(2L) + generateSequence(3L, {
        it + 2
    }).takeWhile {
        it < limit
    }

    fun isPrime(n: Long): Boolean = seq.takeWhile {
        it * it <= n
    }.all {
        n % it != 0L
    }

    return seq.filter(::isPrime).sum() // isPrime을 사용
}

/*
    Extensions
 */

val <T> List<T>.length
    get() = this.size
fun <T> List<T>.length() = this.size

/*
    Lambda
    여러 줄에 걸쳐 쓰는것이 좋다.
 */

fun tripleBadLooking(list: List<Int>): List<Int> = list.map{it*3}
fun triple(list: List<Int>): List<Int> = list.map {
    it * 3
}

val multiplier = 3
fun multiplyAll(list: List<Int>): List<Int> = list.map{it*multiplier}
fun multiplyAlll(list: List<Int>, multiplierr:Int) = list.map{it * multiplierr}

fun main(){
    println(add(1,2))
    println(addd(1,2))
}