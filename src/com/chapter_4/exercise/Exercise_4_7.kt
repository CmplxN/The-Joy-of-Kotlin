package com.chapter_4.exercise

/*
    foldLeft나 foldRight로 reverse 함수를 정의하라. (리스트 뒤집기)
 */

fun <T> prepend(list: List<T>, t:T): List<T> = listOf(t) + list

fun <T> reverse(list: List<T>): List<T> = foldLeft(list,listOf(),::prepend)

fun main(){
    val lst = listOf(1,2,3)
    println(reverse(lst))
    println(reverse<Any?>(listOf()))
}