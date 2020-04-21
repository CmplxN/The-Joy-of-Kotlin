package com.chapter_3.example

/*
    # fun으로 정의한 함수는 선언 방식과 관계없이 진정한 함수임을 보장할 수 없다.(?)
    # 데이터는 함수와 근본적으로 동일하다.
    # 데이터 그 자체도 상수함수로 함수다.
    # 객체 생성자는 함수인가 ? ==> equals의 정의에 따라 결정성을 가짐
 */

class FunFunctions{
    var percent1 = 5
    private var percent2 = 9
    val percent3 = 13

    fun add(a: Int, b: Int): Int = a + b
    fun mult(a:Int, b:Int):Int = 5
    fun div(a:Int ,b:Int):Int = a/b // 제수가 0이면 예외를 던지므로 순수함수가 아니다.
    fun div(a:Double, b:Double):Double = a/b // 제수가 0.0이면 Infinity가 반환되어 순수함수다.

    /*
        percent1이 가변이기에 함수 호출중간에 바뀔 수 있는 등 결정성 결여로 순수함수가 아니다.
        즉 a에 대해서는 순수함수가 아니고 (a, percent1)에 대해서는 순수함수다.
     */
    fun applyTax1(a:Int): Int = a / 100 * (100 + percent1)
    fun applyTax11(ff:FunFunctions, a:Int):Int = a/100*(100+ff.percent1) // (this,a)에 대한 순수함수
    fun applyTax2(a:Int): Int = a / 100 * (100 + percent2) // percent2를 변화시키는 함수가 추가되면 순수함수가 아니게 됨.
    fun applyTax3(a:Int): Int = a / 100 * (100 + percent3) // percent3의 val(불변)으로 (a)에 대한 순수함수

    /*
        인자를 반환하기 전에 변환시키고, 그 결과를 함수 밖에서 관찰할 수 있다.
        ==> 순수함수가 아니다.
     */
    fun append1(i: Int, list:MutableList<Int>): List<Int>{
        list.add(i)
        return list
    }

    /*

        새로운 불변 리스트를 만들어 반환한 것으로 아무것도 변이된게 없다.
        ==> 순수함수다.
     */
    fun append2(i: Int, list:List<Int>) = list + i
}