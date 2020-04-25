package com.chapter_4.exercise

/*
    unfold를 재귀 방식으로 작성하라
 */

fun <T> unfolD(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T> =
        if(!p(seed))
            listOf()
        else
            prepend(unfolD(f(seed),f,p),seed)

fun main(){
    println(unfolD(1,{it+1}){it<11})
}