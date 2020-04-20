package com.chapter_2.example

import java.lang.Exception

/*
    try ... catch ... finally 구조가 값을 돌려주는 식이다.
    모든 예외 검사는 unchecked exception이다.
    # checked exception : 반드시 예외 처리해야한다. 컴파일단계서 확인한다.
 */

val str = "123!"
val num: Int = try{
    str.toInt()
} catch (e: Exception) {
    0
} finally {
    // 이 블록 내의 코드는 항상 실행
}