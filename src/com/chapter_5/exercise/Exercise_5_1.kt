package com.chapter_5.exercise

/*
    리스트의 맨 앞에 원소를 추가하는 cons 함수를 구현하라.
 */

sealed class MyList<out A> {
    abstract fun isEmpty(): Boolean

    companion object {
        operator fun <A> invoke(vararg az: A): MyList<A> =
                az.foldRight(Nil as MyList<A>) { u: A, list: MyList<A> ->
                    Cons(u, list)
                }
        tailrec fun <A> drop(list: MyList<A>, n: Int): MyList<A> = when(list){
            Nil -> list
            is Cons -> if(n<=0) list else drop(list.tail, n - 1)
        }
        tailrec fun <A> dropWhile(list: MyList<A>, f: (A) -> Boolean): MyList<A> = when(list){
            Nil -> list
            is Cons -> if(f(list.head)) dropWhile(list.tail,f) else list
        }
        // list1의 길이만큼 재귀. (스택 크기에 의한 제한 있음)
        fun <A> concat(list1: MyList<A>, list2: MyList<A>): MyList<A> = when(list1){
            Nil -> list2
            is Cons -> concat(list1.tail, list2).cons(list1.head) // list1 맨 뒤부터 하나씩 list2 앞에 붙임
        }
        fun <A> reverse(list: MyList<A>):MyList<A>{
            tailrec fun reverse_(rtn: MyList<A>, list_: MyList<A>): MyList<A> = when(list_){
                Nil -> rtn
                is Cons -> reverse_(rtn.cons(list_.head),list_.tail)
            }
            return reverse_(invoke(),list)
        }
        fun <A> init(list: MyList<A>):MyList<A>{
            return reverse(list).drop(1).reverse()
        }
    }

    internal object Nil : MyList<Nothing>() {
        override fun isEmpty(): Boolean = true
        override fun toString(): String = "[NIL]"
        override fun init(): MyList<Nothing> = throw IllegalStateException("init called on an empty list")
    }

    internal class Cons<A>(internal val head: A, internal val tail: MyList<A>) : MyList<A>() {
        override fun isEmpty(): Boolean = false
        override fun toString(): String = "[${toString("", this)}NIL"
        private tailrec fun toString(rtn: String, list: MyList<A>): String =
                when (list) {
                    is Nil -> rtn
                    is Cons -> toString("$rtn${list.head}, ", list.tail)
                }

        override fun init(): MyList<A> = reverse().drop(1).reverse()
    }

    // Exercise_5_1
    fun cons(t: @UnsafeVariance A): MyList<A> = Cons(t, this)

    // Exercise_5_2
    // 타입으로 경우를 나누는 것은 OOP에서 좋지 못한 스타일
    fun setHead(t: @UnsafeVariance A): MyList<A> = when(this){
        is Cons -> tail.cons(t) // smart cast
        else -> throw IllegalStateException("Cannot set Nil to a certain value")
    }

    //Exercise_5_3
    fun drop(n: Int): MyList<A> {
        tailrec fun drop_(rtn_: MyList<A>, n_: Int): MyList<A> = when(rtn_){
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

    fun newDrop(n: Int): MyList<A> = drop(this, n)

    // Exercise_5_4
    fun dropWhile(p: (A)-> Boolean): MyList<A> = dropWhile(this, p)

    // concat
    fun concat(list: MyList<@UnsafeVariance A>): MyList<A> = concat(this, list)

    // Exercise_5_5
    fun reverse(): MyList<A> = reverse(this)
    abstract fun init(): MyList<A>
}

// Exercise_5_6
fun sum(ints: MyList<Int>): Int = when(ints){
    MyList.Nil -> 0
    is MyList.Cons -> ints.head + sum(ints.tail)
}

// Exercise_5_7
fun product(ints: MyList<Double>): Double {
    fun product_(rtn: Double, list_:MyList<Double>): Double = when(list_){
        MyList.Nil -> rtn
        is MyList.Cons -> product_(rtn * list_.head, list_.tail)
    }
    return product_(1.0,ints)
}

fun main(){
    val test = MyList(1.0, 2.0, 3.0)
    println(product(test))
}