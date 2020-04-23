package com.chapter_4.exercise

/*
    sum, toString, makeString을 정의할 때 쓸 수 있는 꼬리 재귀 제네릭 함수를 만들어라.
    이 함수에 foldLeft라는 이름을 붙이고 이를 사용해 sum, toString, makeString을 정의하라.
 */

fun <T,U> foldLeft(list: List<T>, u: U, f: (U,T) -> U): U {
    tailrec fun foldLeft_(list_: List<T>, u_: U): U =
            if (list_.isEmpty())
                u_
            else
                foldLeft_(list_.tail(), f( u_,list_.head()))
    return foldLeft_(list, u)
}

fun sum(list:List<Int>) = foldLeft(list,0,Int::plus)
fun toString(list:List<Char>) = foldLeft(list,"",String::plus)
fun <T>makeString(list:List<T>, delim: String) =
        foldLeft(list,""){u:String, t:T ->
            if(u.isEmpty())
                t.toString()
            else
                "$u$delim${t.toString()}"
        }

fun main(){
    println(sum(listOf(1,2,9,8,3,7)))
    println(toString(listOf('a','c','b','c')))
    println(makeString(listOf(1,2,9,8,3,7)," and "))
}