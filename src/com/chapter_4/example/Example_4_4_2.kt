package com.chapter_4.example

import java.util.concurrent.ConcurrentHashMap

/*
    자동 메모화 사용하기
    => map을 만들어서 계산한 적이 있으면 계산 안하고 값 리턴
    => 계산한 적 없으면 계산 수행하고 map에 계산 결과 삽입 및 리턴
 */

object Doubler {
    private val cache = ConcurrentHashMap<Int, Int>()
    val double: (Int) -> Int = { cache.computeIfAbsent(it) { it * 2 } }
}

/*
    추상화
    이 코드 문제점 : 입력 가짓수가 너무 많으면 메모리?

 */

class Memoizer<T,U> private constructor(){
    private val cache = mutableMapOf<T,U>()

    private fun doMemoize(f: (T)->U):(T)->U =
            {input->
                cache.computeIfAbsent(input){
                    f(it)
                }
            }

    companion object{
        fun<T,U> memoize(f:(T)->U): (T)->U =
                Memoizer<T,U>().doMemoize(f)
    }
}

fun longComputation(number: Int): Int{
    Thread.sleep(100)
    return number
}
fun longComputationT(number: Int): Int{
    Thread.sleep(100)
    return number * 2
}

fun main(){
    val s1 = System.currentTimeMillis()
    val r1 = longComputation(42)
    val t1 = System.currentTimeMillis() - s1
    val memoizedLongComputation = Memoizer.memoize(::longComputation)
    val s2 = System.currentTimeMillis()
    val r2 = memoizedLongComputation(42)
    val t2 = System.currentTimeMillis() - s2
    val s3 = System.currentTimeMillis()
    val r3 = memoizedLongComputation(42)
    val t3 = System.currentTimeMillis() - s3
    val memoizedLongComputationT = Memoizer.memoize(::longComputation) // 서로 다른 캐시 사용
    val s4 = System.currentTimeMillis()
    val r4 = memoizedLongComputationT(42)
    val t4 = System.currentTimeMillis() - s4
    println("$r1 $t1")
    println("$r2 $t2")
    println("$r3 $t3")
    println("$r4 $t4")

    println(Memoizer.memoize(::longComputation).hashCode())
    println(Memoizer.memoize(::longComputation).hashCode())
    println(443308702.hashCode())
    println(443308702.hashCode())
}