package com.chapter_5.exercise

/*
    Double 리스트에 들어있는 모든 원소의 곱을 계산하는 product를 작성하라.
 */

// Exercise_5_7
fun productr(ints: MyList<Double>): Double {
    fun product_(rtn: Double, list_:MyList<Double>): Double = when(list_){
        MyList.Nil -> rtn
        is MyList.Cons -> product_(rtn * list_.head, list_.tail)
    }
    return product_(1.0,ints)
}