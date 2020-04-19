package com.chapter_2.example

/*
    Kotlin's default collections are immutable
    # See data sharing immutables @Chap 5
 */

fun main(){
    val list1 = listOf(1,2,3)
    val list2 = list1 + 4 // making completely new list
    val list3 = list1 + list2
    println(list1)
    println(list2)
    println(list3)
    val list11 = mutableListOf(1,2,3)
    val list22 = list11 + 4 // return is "List<T>" not MutableList<T>
    list11.add(4) // this changes list11
}