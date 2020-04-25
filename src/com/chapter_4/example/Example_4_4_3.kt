package com.chapter_4.example

/*
    다인자 함수의 메모화
    => 튜플은 다를거 없다.
    => 커리한 함수는 각각 메모화해야한다...
 */

val mhc = Memoizer.memoize{x: Int ->
    Memoizer.memoize{y:Int ->
        longComputation(x) + longComputation(y)
    }
}

fun main(){
    val s1 = System.currentTimeMillis()
    val r1 = mhc(42)(42)
    val t1 = System.currentTimeMillis() - s1
    val s2 = System.currentTimeMillis()
    val r2 = mhc(42)(42)
    val t2 = System.currentTimeMillis() - s2
    val s3 = System.currentTimeMillis()
    val r3 = mhc(42)(41)
    val t3 = System.currentTimeMillis() - s3
    val s4 = System.currentTimeMillis()
    val r4 = mhc(42)(42)
    val t4 = System.currentTimeMillis() - s4
    println("$r1 $t1")
    println("$r2 $t2")
    println("$r3 $t3")
    println("$r4 $t4")
}