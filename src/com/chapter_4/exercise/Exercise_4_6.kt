package com.chapter_4.exercise

/*
    foldRight을 작성하고, 이를 사용해 toString을 구현하라.
    꼬리재귀로 하지 않아도 된다.
 */

fun <T,U> foldRight(list: List<T>, rtn: U, f:(T,U)->U):U=
        if(list.isEmpty())
            rtn
        else
            f(list.head(), foldRight(list.tail(),rtn,f))
fun prepend(c: Char, s: String): String = "$c$s"
fun toSTRING(list:List<Char>): String =
        foldRight(list,"",::prepend)
fun main(){
    println(toSTRING(listOf('1','2','3','a','A')))
}