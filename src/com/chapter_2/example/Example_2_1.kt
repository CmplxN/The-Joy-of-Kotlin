package com.chapter_2.example

 /*
    allocating initial value to a non-nullable variable using lazy.
    we can easily do it by var, but not good for safe programming.
 */

fun main(){
    val name: String by lazy(::getName)
    println("안녕1")
    val name2: String by lazy{name}
    println("안녕2")
    println(name2)
    println(name2)

    // using var on non-nullable
    lateinit var name3: String
    name3 = getName()

    // using var + nullable
    var name4: String? = null
    name4 = getName()
}

fun getName(): String {
    println("getName called")
    return "JTLee"
}