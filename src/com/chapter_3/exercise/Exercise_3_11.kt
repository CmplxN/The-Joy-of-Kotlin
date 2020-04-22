package com.chapter_3.exercise

/*
    커리한 함수의 두 인자의 순서를 뒤바꾼 새로운 함수를 반환하는 fun 함수를 작성하라.
 */

fun <T,U,V> revCur():((T)->(U)->(V)) -> (U)->(T)->V = {
    { u: U ->
        { t: T ->
            it(t)(u)
        }
    }
}

fun test(a:Int, b:Int): Int = 2 * a + b * b

fun main(){
    val newSoT = toCur<Int,Int,Int>()(::test)
    println(newSoT(2)(3))
    println(revCur<Int,Int,Int>()(newSoT)(2)(3))
}