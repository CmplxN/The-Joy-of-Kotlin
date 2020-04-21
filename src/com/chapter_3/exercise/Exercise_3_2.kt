package com.chapter_3.exercise

/*
    compose 함수를 타입 파라미터를 사용하는 다형적 함수로 만들어라.
    즉 일반화 시켜봐라
 */

fun <T,U,V> compose(f:(T)->U, g:(V)->T):(V)->U = {
    f(g(it))
}