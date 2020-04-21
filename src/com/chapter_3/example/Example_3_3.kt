package com.chapter_3.example

import com.chapter_3.exercise.add

/*
    # 고차함수 : 함수를 인자로 받거나 함수를 결과로 돌려주는 함수
 */

fun main(){
    println(add(3)(5)) // curried form
}