package com.chapter_4.example

data class Tuple4<T,U,V,W>(val t:T,val u:U, val v:V, val w:W)

val ft = {(a,b,c,d):Tuple4<Int,Int,Int,Int> ->
    longComputation(a)+ longComputation(b) + longComputation(c) + longComputation(d)}

val ftm = Memoizer.memoize(ft)

fun main(){
    val s1 = System.currentTimeMillis()
    val r1 = ftm(Tuple4(1,2,3,4))
    val t1 = System.currentTimeMillis() - s1
    val s2 = System.currentTimeMillis()
    val r2 = ftm(Tuple4(1,2,3,4))
    val t2 = System.currentTimeMillis() - s2

    println("$r1 $t1")
    println("$r2 $t2")
}