package com.chapter_3.exercise
/*
    두 함수를 합성하는 함수 값을 만들라.
    예를 들어 3.2.8절 예제에서 봤던 square와 triple을 함수값으로 다시 정의하고,
    이 둘을 합성한 squareOfTriple을 만들라.
 */

val compose = {f:(Int)->Int ->
    {g:(Int)->Int ->
        {x:Int->
            f(g(x))
        }
    }
}
typealias intUnaryOp = (Int) -> Int
val square: intUnaryOp = {it*it} // 익명 함수에 이름 부여
val triple: intUnaryOp = {3*it} // 익명 함수에 이름 부여
val squareOfTriple: intUnaryOp = compose(square)(triple)

fun main(){
    println(squareOfTriple(2))
}
