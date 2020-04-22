package com.chapter_3.exercise

/*
    (A,B)에서 C로 가는 함수를 커리한 함수로 바꾸는 함수를 작성하라
 */

fun <A,B,C> toCur(): ((A,B)->C) -> ((A) -> (B) -> C) = {
    {a:A->
        {b:B->
            it(a,b)
        }
    }
}

fun myAdd(a:Int, b:Int): Int = a + b
val newAdd = toCur<Int,Int,Int>()(::myAdd)
fun main(){
    println(newAdd(3)(4))
}