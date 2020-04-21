package com.chapter_3.exercise
/*
    다형적 compose 함수를 작성하라.
 */

fun <T, U, V>compose() = {f:(T)->U ->
    {g:(V)->T ->
        {x:V->
            f(g(x))
        }
    }
}

/*
    # 코틀린은 다형적 프로퍼티를 제공하지 않는다..
    # 다형성 제공 : 함수(fun), 클래스, 인터페이스
val <T, U, V>compose = {f:(T)->U ->
    {g:(V)->T ->
        {x:V->
            f(g(x))
        }
    }
}
*/

val squareOfTriplE = compose<Int,Int,Int>()(square)(triple)

fun main(){
    println(squareOfTriplE(2))
}