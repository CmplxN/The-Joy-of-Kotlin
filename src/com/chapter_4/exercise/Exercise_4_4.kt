package com.chapter_4.exercise

import java.lang.IllegalArgumentException

/*
    makeStrinG 함수의 꼬리재귀 버전을 작성하라.
 */

fun <T> List<T>.head(): T =
        if(isEmpty())
            throw IllegalArgumentException("head called on empty list")
        else
            get(0)
fun <T> List<T>.tail(): List<T> =
        if(isEmpty())
            throw IllegalArgumentException("tail called on empty list")
        else
            drop(1)

fun <T> makeStrinG(list: List<T>, delim: String): String =
        when{
            list.isEmpty() -> ""
            list.tail().isEmpty() ->
                "${list.head()}${makeStrinG(list.tail(),delim)}"
            else -> "${list.head()}$delim${makeStrinG(list.tail(),delim)}"
        }

fun <T> makeStringg(list: List<T>, delim: String): String{
    tailrec fun makeString_(rtn: String, next:List<T>):String=
            when{
                next.isEmpty() -> rtn
                rtn.isEmpty() -> makeString_("${next.head()}",next.tail())
                else -> makeString_("$rtn$delim${next.head()}",next.tail())
            }
    return makeString_("",list)
}

fun main(){
    val test = listOf(1,2,3,4,5)
    println(makeStrinG(test," and "))
    println(makeStringg(test," and "))
}