package com.chapter_5.exercise

/*
    리스트의 맨 앞에 원소를 추가하는 cons 함수를 구현하라.
 */

/*
    그 외 다른 Chap 5 Exercise 모음
 */

sealed class MyList<out A> {
    abstract fun isEmpty(): Boolean

    companion object {
        tailrec fun <A> drop(list: MyList<A>, n: Int): MyList<A> = when (list) {
            Nil -> list
            is Cons -> if (n <= 0) list else drop(list.tail, n - 1)
        }

        tailrec fun <A> dropWhile(list: MyList<A>, f: (A) -> Boolean): MyList<A> = when (list) {
            Nil -> list
            is Cons -> if (f(list.head)) dropWhile(list.tail, f) else list
        }

        // list1의 길이만큼 재귀. (스택 크기에 의한 제한 있음)
        fun <A> concatO(list1: MyList<A>, list2: MyList<A>): MyList<A> = when (list1) {
            Nil -> list2
            is Cons -> concat(list1.tail, list2).cons(list1.head) // list1 맨 뒤부터 하나씩 list2 앞에 붙임
        }

        fun <A> reverse(list: MyList<A>): MyList<A> {
            tailrec fun reverse_(rtn: MyList<A>, list_: MyList<A>): MyList<A> = when (list_) {
                Nil -> rtn
                is Cons -> reverse_(rtn.cons(list_.head), list_.tail)
            }
            return reverse_(invoke(), list)
        }

        fun <A> init(list: MyList<A>): MyList<A> {
            return reverse(list).drop(1).reverse()
        }

        fun <A, B> foldRight(list: MyList<A>, identity: B, f: (A) -> (B) -> B): B =
                when (list) {
                    Nil -> identity
                    is Cons -> f(list.head)(foldRight(list.tail, identity, f))
                }

        operator fun <A> invoke(vararg az: A): MyList<A> =
                az.foldRight(Nil) { a: A, list: MyList<A> -> Cons(a, list) }

        tailrec fun <A, B> foldLeft(acc: B, list: MyList<A>, f: (B) -> (A) -> B): B =
                when (list) {
                    Nil -> acc
                    is Cons -> foldLeft(f(acc)(list.head), list.tail, f)
                }

        tailrec fun <A, B> coFoldRight(acc: B, list: MyList<A>, f: (A) -> (B) -> B): B =
                when (list) {
                    Nil -> acc
                    is Cons -> coFoldRight(f(list.head)(acc), list.tail, f)
                }

        // Exercise_5_14
        fun <A> concat(list1: MyList<A>, list2: MyList<A>): MyList<A> =
                list1.coFoldRight(list2) { x -> { acc -> Cons(x, acc) } }
        //list1.reverse().foldLeft(list2){u->{v->u.cons(v)}}

        // Exercise_5_15
        fun <A> flatten(list: MyList<MyList<A>>): MyList<A> = list.coFoldRight(Nil as MyList<A>) { x -> x::concat }
    }

    internal object Nil : MyList<Nothing>() {
        override fun isEmpty(): Boolean = true
        override fun toString(): String = "[NIL]"
        override fun init(): MyList<Nothing> = throw IllegalStateException("init called on an empty list")
    }

    internal class Cons<A>(internal val head: A, internal val tail: MyList<A>) : MyList<A>() {
        override fun isEmpty(): Boolean = false
        override fun toString(): String = "[${toString("", this)}NIL]"
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
    fun setHead(t: @UnsafeVariance A): MyList<A> = when (this) {
        is Cons -> tail.cons(t) // smart cast
        else -> throw IllegalStateException("Cannot set Nil to a certain value")
    }

    //Exercise_5_3
    fun drop(n: Int): MyList<A> {
        tailrec fun drop_(rtn_: MyList<A>, n_: Int): MyList<A> = when (rtn_) {
            Nil -> rtn_
            is Cons -> {
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
    fun dropWhile(p: (A) -> Boolean): MyList<A> = dropWhile(this, p)

    // concat
    fun concat(list: MyList<@UnsafeVariance A>): MyList<A> = concat(this, list)

    // Exercise_5_5
    fun reverseFive(): MyList<A> = reverse(this)
    abstract fun init(): MyList<A>

    fun <B> foldRight(identity: B, f: (A) -> (B) -> B): B = foldRight(this, identity, f)

    // Exercise_5_8
    fun lengthr(): Int = foldRight(0) { { it + 1 } }

    // Exercise_5_9
    fun <B> foldLeft(identity: B, f: (B) -> (A) -> B): B = foldLeft(identity, this, f)

    // Exercise_5_10
    fun length(): Int = foldLeft(0) { x->{x+1} }

    // Exercise_5_11
    fun reverse(): MyList<A> = foldLeft(Nil as MyList<A>/*반환타입 명시*/){acc -> {x ->acc.cons(x)}}

    // Exercise_5_12
    fun <B> foldRightViaFoldLeft(identity: B, f: (A) -> (B) -> B): B =
            reverse().foldLeft(identity){a->{b->f(b)(a)}}

    // Exercise_5_13
    fun <B> coFoldRight(acc: B, f: (A) -> (B) -> B): B = coFoldRight(acc,this,f)

    // Exercise_5_18
    fun <B> map(f: (A) -> B): MyList<B> = this.coFoldRight(Nil as MyList<B>){u->{v->v.cons(f(u))}}

    // Exercise_5_19
    fun filter(p: (A) -> Boolean): MyList<A> =
            this.coFoldRight(Nil as MyList<A>){u->{v->if(p(u)) v.cons(u) else v}}

    // Exercise_5_20
    fun <B> flatMap(f: (A) -> MyList<B>): MyList<B> = flatten(map(f))

    // Exercise_5_21
    fun filterN(p: (A) -> Boolean): MyList<A> = flatMap{u->if(p(u)) MyList(u) else Nil}
}

// Exercise_5_10

fun sum(list: MyList<Int>): Int = list.foldLeft(0){acc->{x->acc+x}}

fun product(list: MyList<Double>): Double = list.foldLeft(1.0){acc->{x->acc*x}}

// Exercise_5_16
fun triple(list: MyList<Int>): MyList<Int> = list.coFoldRight(List.Nil as MyList<Int>){u->{v->v.cons(3*u)}}

// Exercise_5_17
fun doubleToString(list: MyList<Double>): MyList<String> =
        list.coFoldRight(List.Nil as MyList<String>){u->{v->v.cons(u.toString())}}

fun main(){
    val test = MyList(1,2,3,4)
    println(test)
    println(test.reverse())
}