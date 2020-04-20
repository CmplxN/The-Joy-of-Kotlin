package com.chapter_2.example

/*
    만약 인터페이스가 T 타입의 값을 소비하는 동시에 생산한다면 변성을 지정할 수 없다.
 */

interface BAG<T> { // 선언지점 변성이 아니다.
    fun get(): T
    fun use(t: T): Boolean
}

class BAGImpl: BAG<MyClassParent>{
    override fun get(): MyClassParent {
        return MyClassParent()
    }

    override fun use(t: MyClassParent): Boolean {
        return true
    }

}

fun useBAG(bag: BAG<in MyClass>):Boolean{
    return true
}

////////////////////////////////////////////////////////////////

class BAGImpl2: BAG<MyClass>{
    override fun get(): MyClass {
        return MyClass()
    }

    override fun use(t: MyClass): Boolean {
        return true
    }

}

fun createBAG():BAG<out MyClassParent> {
    return BAGImpl2()
}

val bag3 = useBAG(BAGImpl())
val bag4 = createBAG()
