package com.chapter_3.exercise

/*
    함수를 합성하되 적용 순서가 반대인 higherAndThen함수를 정의하라.
 */

fun <T, U, V>higherAndThen() = {f:(T)->U ->
    {g:(U)->V ->
        {x:T->
            g(f(x))
        }
    }
}

val tripleOfSquare = higherAndThen<Int,Int,Int>()(square)(triple)

fun main(){
    println(tripleOfSquare(2))
}