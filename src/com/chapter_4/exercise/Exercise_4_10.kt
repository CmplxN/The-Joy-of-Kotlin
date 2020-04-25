package com.chapter_4.exercise

/*
    임의의 타입과 조건에 대해 작동하는 range와 비슷한 함수를 만들어라.
    시그니쳐는 다음과 같다.
    fun <T> unfold(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T>
 */

fun <T> unfold(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T>{
    val rtn = mutableListOf<T>()
    var elm = seed
    while(p(elm)){
        rtn.add(elm)
        elm = f(elm)
    }
    return rtn
}