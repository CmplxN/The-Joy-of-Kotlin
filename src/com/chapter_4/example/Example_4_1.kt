package com.chapter_4.example

/*
    # 공재귀 : corecursion. 한 단계의 출력을 다음 단계의 입력으로 사용하는 곗나 단계를 합성. [첫 단계]부터 계산
    + 자신을 호출하는 함수는 반드시 반환 타입을 명시해야한다.
    + 루프로 변환할 수 있다.
 */

fun append(s: String, c: Char): String = "$s$c"

fun toString(list: List<Char>, s: String): String =
        if (list.isEmpty())
            s
        else
            toString(list.subList(1,list.size), append(s,list[0]))
fun toStringg(list: List<Char>, s: String): String =
        if (list.isEmpty())
            s
        else
            toStringg(list.drop(1), append(s,list.first()))
fun toStringgg(list: List<Char>, s: String): String =
        if (list.isEmpty())
            s
        else
            toStringgg(list.drop(1), append(s,list.first()))
fun toStrinG(list: List<Char>): String{ //
    return toStringgg(list, "")
}

/*
    # 재귀 : recursion. 같은 연산을 사용하지만 [마지막] 단계부터 계산을 시작.
    최종 조건 만족까지는 아무 계산도 하지 않는다. 그러므로 중간 결과가 저장되어야 한다.
    JVM은 중간 단계를 저장하는데 사용하는 메모리가 작기에 이는 좋지 않다.
 */

fun prepend(s: String, c: Char): String = "$c$s"

fun ToString(list: List<Char>): String =
        if(list.isEmpty())
            ""
        else
            prepend(ToString(list.subList(1, list.size)), list[0])