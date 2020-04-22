package com.chapter_3.example

import com.chapter_3.exercise.add
import com.chapter_3.exercise.compose
import com.chapter_3.exercise.higherAndThen

/*
    # 고차함수 : 함수를 인자로 받거나 함수를 결과로 돌려주는 함수
    # 익명함수 : 한 번만 사용하는 함수는 익명함수로 정의하라... 나는 함수 참조쓸래
 */

val cosValue = compose(Math::sin) { x:Double -> Math.PI / 2 - x}
val cosValuee = higherAndThen<Double,Double,Double>()()
                                    { x:Double->Math.PI / 2 - x}(Math::sin)
val cosValueee = (compose<Double,Double,Double>()
            (Math::sin)){ Math.PI / 2 - it} // compose에 ()를 안하면 람다식이 어디에 들어가는지 모름.

fun cos(arg:Double) = compose<Double,Double,Double>({x->Math.PI / 2 - x},{y->Math.sin(y)})(arg)

fun main(){
    println(add(3)(5)) // curried form
    println(cos(0.0))
    println(cosValue(0.0))
    println(cosValuee(0.0))
    println(cosValueee(0.0))
}