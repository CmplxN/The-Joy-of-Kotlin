package com.chapter_5.exercise

/*
    리스트의 맨 앞에 원소를 추가하는 cons 함수를 구현하라.
 */

sealed class MyList<T> {
    abstract fun isEmpty(): Boolean

    companion object {
        operator fun <U> invoke(vararg az: U): MyList<U> =
                az.foldRight(Nil as MyList<U>) { u: U, list: MyList<U> ->
                    Cons(u, list)
                }
        tailrec fun <U> drop(list: MyList<U>, n: Int): MyList<U> = when(list){
            Nil -> list
            is Cons -> if(n<=0) list else drop(list.tail, n - 1)
        }
        tailrec fun <U> dropWhile(list: MyList<U>, f: (U) -> Boolean): MyList<U> = when(list){
            Nil -> list
            is Cons -> if(f(list.head)) dropWhile(list.tail,f) else list
        }
        // list1의 길이만큼 재귀. (스택 크기에 의한 제한 있음)
        fun <U> concat(list1: MyList<U>, list2: MyList<U>): MyList<U> = when(list1){
            Nil -> list2
            is Cons -> concat(list1.tail, list2).cons(list1.head)
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

    // Exercise_5_1
    fun cons(t: T): MyList<T> = Cons(t, this)

    // Exercise_5_2
    // 타입으로 경우를 나누는 것은 OOP에서 좋지 못한 스타일
    fun setHead(t: T): MyList<T> = when(this){
        is Cons -> tail.cons(t) // smart cast
        else -> throw IllegalStateException("Cannot set Nil to a certain value")
    }

    //Exercise_5_3
    fun drop(n: Int): MyList<T> {
        tailrec fun drop_(rtn_: MyList<T>, n_: Int): MyList<T> = when(rtn_){
            Nil -> rtn_
            is Cons ->{
                if (n_ > 0)
                    drop_(rtn_.tail, n_ - 1)
                else
                    rtn_
            }
        }
        return drop_(this, n)
    }

    fun newDrop(n: Int): MyList<T> = drop(this, n)

    // Exercise_5_4
    fun dropWhile(p: (T)-> Boolean): MyList<T> = dropWhile(this, p)

    // concat
    fun concat(list: MyList<T>): MyList<T> = concat(this, list)
}

fun main(){

}