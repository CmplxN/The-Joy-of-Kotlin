package com.chapter_2.example

/*
    스마트 캐스트 : is 를 이용해서 자동으로 타입을 변환해줌.

    강제 타입 변환
    as : 타입 변환이 불가능하면 ClassCastException
    as? : 타입 변환이 불가능하면 null

    하지만 타입검사하고 강제 타입 변환 하는 것은 객체지향 설계가 잘못 되었다는 뜻.
 */

val payload = readLine()

val lenght: Int = if (payload is String)
    payload.length
else
    -1

val result: String = payload as String
val resultt: String? = payload as? String