package com.chapter_6.example

/*
    List와 비슷한 구조의 Option 타입을 만든다.
    데이터가 있는 겨웅와 없는 경우 두가지로 나뉜 sealed class로 구성한다.
    None : 데이터의 부재를 나타내는 하위 클래스
    Some : 데이터의 존재를 나나태는 하위 클래스 + 대상 값이 저장
 */

sealed class Option<out A> {
    abstract fun isEmpty(): Boolean

    internal object None : Option<Nothing>() {
        override fun isEmpty(): Boolean = true
        override fun toString(): String = "None"
        override fun equals(other: Any?): Boolean = other === None
        override fun hashCode(): Int = 0
    }

    internal data class Some<out A>(internal val value: A) : Option<A>() {
        override fun isEmpty(): Boolean = false
    }

    companion object {
        operator fun <A> invoke(a: A? = null): Option<A> =
                when (a) {
                    null -> None
                    else -> Some(a)
                }
    }

    // 여기에 값을 돌려주는 함수를 만들면? ==> 안전한 값으로 바꾸지 전에는 절대로 안전한 환경에서 꺼내선 안된다.

    // Exercise_6_1
    fun getOrElse(default: @UnsafeVariance A): A = when (this) {
        is None -> default
        is Some -> value
    }

    // Exercise_6_2
    fun getOrElse(default: () -> @UnsafeVariance A): A = when (this) {
        is None -> default()
        is Some -> value
    }

    // Exercise_6_3
    fun <B> map(f: (A) -> B): Option<B> = when (this) {
        is Some -> Some(f(value))
        else -> None
    }

    // Exercise_6_4
    fun <B> flatMap(f: (A) -> Option<B>): Option<B> = map(f).getOrElse { None }

    // Exercise_6_5
    fun orElse(default: () -> Option<@UnsafeVariance A>): Option<A> = map { this }.getOrElse(default)

    // Exercise_6_6
    fun filter(p: (A) -> Boolean): Option<A> = flatMap { x: A -> if (p(x)) this else None }
}

fun getDefault(): Int = throw RuntimeException()

fun max(list:List<Int>): Option<Int> = Option(list.max())

fun main(){
    val toons: Map<String, Toon> = mapOf(
            "Mickey" to Toon("Mickey", "Mouse", "mickey@disney.com"),
            "Minnie" to Toon("Minnie", "Mouse"),
            "Donald" to Toon("Donald", "Duck", "donald@disney.com")
    )

    val mickey = toons["Mickey"]?.email ?: "NoData"
    val minnie = toons["Minnie"]?.email ?: "NoData"
    val goofy = toons["Goofy"]?.email ?: "NoData"

    println(mickey)
    println(minnie)
    println(goofy)
}