package com.chapter_4.exercise

/*
    unfold를 꼬리 재귀로 작성하라.
 */

fun <T> unfoldR(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T> {
    fun unfoldR_(rtn: List<T>, next: T): List<T> =
            if (!p(next))
                rtn
            else
                unfoldR_(rtn + next, f(next))
    return unfoldR_(listOf(), seed)
}