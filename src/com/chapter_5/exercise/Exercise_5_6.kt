package com.chapter_5.exercise

/*
    sum 구하기.
    variance 버전이 아닌
    Empty 추상 클래스 도입 버전.
 */


sealed class List<A> {

    abstract fun isEmpty(): Boolean

    abstract fun init(): List<A>

    fun setHead(a: @UnsafeVariance A): List<A> = when (this) {
        is Cons -> Cons(a, this.tail)
        else -> throw IllegalStateException("setHead called on an empty list")
    }

    fun cons(a: @UnsafeVariance A): List<A> = Cons(a, this)

    abstract fun concat(list: List<A>): List<A>

    fun drop(n: Int): List<A> = drop(this, n)

    fun dropWhile(p: (A) -> Boolean): List<A> = dropWhile(this, p)

    fun reverse(): List<A> = reverse(List.invoke(), this)

    fun reverse2(): List<A> {
        tailrec fun <A> reverse2(acc: List<A>, list: List<A>): List<A> = when (list) {
            is Cons -> reverse2(Cons(list.head, acc), list.tail)
            else -> acc
        }
        return reverse2(List.invoke(), this)
    }

    abstract class Empty<A> : List<A>(){
        override fun concat(list: List<A>): List<A> = list
    }

    internal object Nil: Empty<Nothing>() {

        override fun init(): List<Nothing> = throw IllegalStateException("init called on an empty list")

        override fun isEmpty() = true

        override fun toString(): String = "[NIL]"
    }

    internal class Cons<A>(internal val head: A, internal val tail: List<A>): List<A>() {

        override fun init(): List<A> = reverse().drop(1).reverse()

        override fun isEmpty() = false

        override fun toString(): String = "[${toString("", this)}NIL]"

        private tailrec fun toString(acc: String, list: List<A>): String = when (list) {
            is Cons -> toString("$acc${list.head}, ", list.tail)
            else -> acc
        }

        override fun concat(list: List<A>): List<A> = Cons(this.head, list.concat(this.tail))
    }

    companion object {

        fun <A> cons(a: A, list: List<A>): List<A> = Cons(a, list)

        tailrec fun <A> drop(list: List<A>, n: Int): List<A> = when (list) {
            is Cons -> if (n <= 0) list else drop(list.tail, n - 1)
            else -> list
        }

        tailrec fun <A> dropWhile(list: List<A>, p: (A) -> Boolean): List<A> = when (list) {
            is Cons -> if (p(list.head)) dropWhile(list.tail, p) else list
            else -> list
        }

        fun <A> concat(list1: List<A>, list2: List<A>): List<A> = when (list1) {
            is Cons -> Cons(list1.head, concat(list1.tail, list2))
            else -> list2
        }

        tailrec fun <A> reverse(acc: List<A>, list: List<A>): List<A> = when (list) {
            is Cons -> reverse(Cons(list.head, acc), list.tail)
            else -> acc
        }

        operator fun <A> invoke(vararg az: A): List<A> =
                az.foldRight(Nil as List<A>){a: A, list:List<A> -> Cons(a,list)}
        // Use this implementation after adding variance handling to the class
        // az.foldRight(Nil, { a: A, list: List<A> -> Cons(a, list) })
    }
}

fun sum(ints: List<Int>):Int = when(ints){
    is List.Cons -> ints.head + sum(ints.tail)
    else -> 0
}


