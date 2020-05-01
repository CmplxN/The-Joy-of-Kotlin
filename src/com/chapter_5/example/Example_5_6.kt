package com.chapter_5.example

import com.chapter_5.exercise.MyList

/*
    만든 List에 대한 sum, product -> operation -> foldRight
    identity
 */

fun <T,U> operation(list: MyList<T>, identity: U, operator: (T) -> (U) -> U): U =
        when(list){
            MyList.Nil -> identity
            is MyList.Cons -> operator(list.head)(operation(list.tail,identity,operator))
        }

fun <T,U> foldRight(list: MyList<T>, identity: U, f: (T) -> (U) -> U): U =
        when(list){
            MyList.Nil -> identity
            is MyList.Cons -> f(list.head)(operation(list.tail,identity,f))
        }

fun <T,U> sum(list:MyList<Int>): Int =
        foldRight(list, 0){x->{y->x+y}}
fun <T,U> product(list:MyList<Double>): Double =
        foldRight(list, 1.0){x->{y->x*y}}

fun main(){
    println(foldRight(MyList(2,3,4,5),MyList()){x:Int ->
        {y:MyList<Int>->
            y.cons(x)
        }
    })
}