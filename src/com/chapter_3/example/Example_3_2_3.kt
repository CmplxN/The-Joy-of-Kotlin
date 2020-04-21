package com.chapter_3.example

/*
    fun ~식으로 쓰지 않고 정의하면 함수를 데이터처럼 쓸 수 있다.
    람다함수의 맨 마지막 줄을 반환으로 읽는다.
    인자가 한개면 인자를 그냥 it으로 표시할 수 있다.
    그러나 정의할 때 람다함수가 다중으로 들어가면 it은 가독성을 해친다.
 */

class Ref{
    fun ref(n:Int): Int = n * 2
    companion object{
        fun reff(n:Int):Int = n * 2
    }
}
fun Double(x:Int):Int = x * 2
val double  = ::Double
val doublee ={it:Int ->Double(it)}
val doubleee = Ref()::ref
val doubleC = (Ref)::reff
val doublec = Ref.Companion::reff
val wtf = Ref::ref