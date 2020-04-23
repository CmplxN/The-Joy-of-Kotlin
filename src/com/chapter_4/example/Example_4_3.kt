package com.chapter_4.example

import java.lang.IllegalArgumentException

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
fun sum(list: List<Int>): Int{
    tailrec fun helper(rtn: Int,next: List<Int>): Int =
            if(next.isEmpty())
                rtn
            else
                helper(rtn+next.head(),next.tail())
    return helper(0,list)
}