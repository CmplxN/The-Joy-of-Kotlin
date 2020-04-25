package com.chapter_4.exercise

/*
    (T) -> U 타입의 함수를 List<T> 타입의 모든 원소에 적용해 만든
    List<U> 타입 리스트를 돌려주는 map 함수를 만들어라.
 */

fun <T,U> mapp(list: List<T>, f: (T) -> U): List<U>{
    fun mapp_(rtn: List<U>): List<U> =
            if(rtn.size >= list.size)
                rtn
            else
                mapp_(rtn + f(list[rtn.size]))
    return mapp_(listOf())
}

// 앞서 구현했던 foldLeft를 이용한 풀이  ==> map도 결국 fold다.

fun <T,U> Map(list: List<T>, f: (T) -> U): List<U> =
        foldLeft(list, listOf()){lst, elm -> lst + f(elm)}

fun main(){
    val test = listOf(1,3,5,7,9)
    println(mapp(test) {it*it})
    println(Map(test) {it*it})
}