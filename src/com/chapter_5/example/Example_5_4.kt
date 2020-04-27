package com.chapter_5.example

import com.chapter_5.exercise.MyList

/*
    불변, 영속 단일 연결 리스트 만들기

    # sealed class를 통해 하위 타입을 제한한다. (Nil과 Cons만 가능)
    # 하위 타입 생성자는 모두 private으로 하고 invoke를 따로 정의해서 invoke로 생성자 호출
    # sealed 클래스는 암묵적으로 추상클래스이고, 생성자는 암묵적으로 비공개다.
    # foldRight : 오른쪽 부터 fold
 */

/*
Exercise에서 구현하는걸로...
sealed class MyList<T> {
    abstract fun isEmpty(): Boolean

    companion object {
        operator fun <U> invoke(vararg az: U): MyList<U> =
                az.foldRight(Nil as MyList<U>) { u: U, list: MyList<U> ->
                    Cons(u, list)
                }
    }

    private object Nil : MyList<Nothing>() {
        override fun isEmpty(): Boolean = true
        override fun toString(): String = "[NIL]"
    }

    private class Cons<T>(internal val head: T, internal val tail: MyList<T>) : MyList<T>() {
        override fun isEmpty(): Boolean = false
        override fun toString(): String = "[${toString("", this)}NIL"
        private tailrec fun toString(rtn: String, list: MyList<T>): String =
                when (list) {
                    is Nil -> rtn
                    is Cons -> toString("$rtn${list.head}, ", list.tail)
                }
    }
}
*/
fun main() {
    val list = MyList(1, 2, 3)
}