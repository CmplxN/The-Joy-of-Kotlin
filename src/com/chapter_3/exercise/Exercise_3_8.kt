package com.chapter_3.exercise

/*
    인자를 두개 받는 fun 함수를 작성하라. 이 함수의 두번째 인자는 인자를 두개 받는 커리한 함수고,
    첫째 인자의 타입은 둘째 인자(함수 값)의 둘째 인자와 같은 타입이다.
    이 함수는 둘째 인자의 둘째 인자에 첫째 인자를 적용한 결과(함수 값)을 돌려준다.
 */

fun <T,U,V> solvE(u: U, f:(T)->(U)->V): (T) -> V = { x: T ->
    f(x)(u)
}

fun main(){
    val h = solvE(3, add)(4)
    println(h)
}