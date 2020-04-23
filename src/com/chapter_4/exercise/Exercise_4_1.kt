package com.chapter_4.exercise

/*
    양의 정수에 대해 작동하는 공재귀 add함수를 작성해라. 주어지는 inc, dec함수만 사용해라.
 */

fun inc(n:Int):Int = n+1
fun dec(n:Int):Int = n-1
tailrec fun add(a:Int,b:Int):Int{
    return if(b == 0) a else add(inc(a),dec(b))
}

fun main(){
    println(add(7,9))
}