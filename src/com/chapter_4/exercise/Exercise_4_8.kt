package com.chapter_4.exercise

/*
    두 리스트를 리스트 간 연결하지 않고
    리스트 뒤에 원소를 덧붙이는 +를 사용해 reversE 함수를 만들어라.
 */

fun <T> prepenD(list: List<T>, t:T): List<T> = foldLeft(list, listOf(t)){lst, elm ->  lst + elm}

fun <T> reversE(list: List<T>): List<T> = foldLeft(list,listOf(),::prepenD)

fun main(){
    val lst = listOf(1,2,3)
    println(reversE(lst))
    println(reversE<Any?>(listOf()))
}