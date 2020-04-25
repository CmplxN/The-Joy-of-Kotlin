package com.chapter_4.exercise

/*
    앞 절에서 정의한 함수들을 바탕으로 range의 재귀 버전을 작성하라.
 */

fun rangeR(start: Int, end: Int): List<Int> =
        if(end<=start)
            listOf()
        else
            prepend(rangeR(start + 1, end),start)

fun main(){
    println(rangeR(1,9))
}