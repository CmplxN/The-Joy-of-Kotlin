package com.chapter_4.exercise

/*
    시작값, 끝값, x -> x + 1이라는 함수로 리스트를 생성하는 함수를 루프 기반으로 작성하라.
    이 함수에 range라는 이름을 붙여라. 시그니쳐는 다음과 같다.
    fun range(start: Int, end: Int): List<Int>
 */

fun range(start: Int, end: Int): List<Int>{
    val rtn = mutableListOf<Int>()
    var index = start
    while(index < end) rtn.add(index++)
    return rtn
}