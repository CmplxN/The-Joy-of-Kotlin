package com.chapter_4.exercise

/*
    unfold로 rangE를 구현하라.
 */

fun rangE(start: Int, end: Int): List<Int> = unfold(start,{it + 1}){it<end}