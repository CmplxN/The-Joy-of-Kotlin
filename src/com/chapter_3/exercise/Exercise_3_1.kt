package com.chapter_3.exercise

/*
    Int에서 Int로 가는 함수의 합성을 허용하는 compose 함수를 작성하라 (fun)
    함수 적용을 합성하는 것이 아니라 함수에 대한 이항연산이다.
 */

fun compose(f: (Int) -> Int, g: (Int) -> Int):(Int) -> Int = {f(g(it))}