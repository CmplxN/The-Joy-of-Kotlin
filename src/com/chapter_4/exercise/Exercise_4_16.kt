package com.chapter_4.exercise

/*
    unfold처럼 작동하는 iterate 함수를 작성하라.
    조건을 만족할 때 까지 자기 자신을 재귀 호출하는 unfold와 달리
    iterate는 주어진 횟수만큼 자신을 재귀호출한다.
 */

fun <T> iterate(seed: T, f: (T) -> T, n: Int): List<T> {
    tailrec fun iterate_(rtn: List<T>, curN: Int, curE: T): List<T> =
            if (curN >= n)
                rtn
            else
                iterate_(rtn + curE, curN + 1, f(curE))
    return iterate_(listOf(), 0, seed)
}