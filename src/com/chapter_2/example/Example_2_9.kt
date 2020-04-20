package com.chapter_2.example

/*
    제어구조는 사실 피하는 것이 좋다. 버그의 주요 원인이다.
    # if ... else는 표현식으로 쓸 수 있다.
    # switch ... case 격의 when 또한 표현식이다.
 */

val a = 6
val b = 5
val percent = if (b != 0) {
    val temp = a / b
    temp * 100
} else {
    // if 안에 effect를 넣을 수는 있지만 지양하라.
    0
}

val country = "Korea"
val capital = when(country){
    "Korea" -> "Seoul"
    else -> "nowhere"
}
val sudo = when{
    country == "Korea" -> "Seoul"
    else -> "nowhere"
}

/*
    while은 여타 언어와 같은 제어구조다.
    for에는 조건을 넣을 수 없으며 결국 range의 iteration이다. (downTo, until, step 등 사용)
    이또한 익숙해지면 for보다는 fold나 reduce를 사용해보자.
 */