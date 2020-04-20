package com.chapter_2.example

/*
    s?.length ==> 1. null if s is null
                  2. s.length if s is not null
    map[companyName]!! ==> NPE if map[companyName] is null

    # 엘비스 연산자 null이 있을 때 특별한 기본 값을 사용하고자 할 떄 사용
    val city: City = map[company]?.manager?.address?.city ?: CITY.UNKNOWN
 */

val s: String? = readLine()
val l = s?.length