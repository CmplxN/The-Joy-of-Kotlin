package com.chapter_2.example

/*
    공변성(covariance) : Red가 Color의 하위타입일 떄 Matcher<Red>가 Matcher<Color>의 하위타입
                        이런 경우 Matcher<T>는 타입 파라미터 T에 대해 공변성이다.
                        # out 으로 표현
    반공변성(contravariance) : Red가 Color의 하위타입일 때 Matcher<Color>가 Matcher<Red>의 하위타입
                             이런 경우 Matcher<T>는 타입 파라미터 T에 대해 반공변성이다.
                             # in 으로 표현
    무공변성(invariance) : Red가 Color의 하위타입일 때 Matcher<Color>와 Matcher<Red>는 상하위 관계없음
                         런타임에 같은 타입이 된다해도 컴파일 시점에서 전혀 다른 타입
 */


/*
    # MutableList<Any>가 MutableList<String>의 상위타입처럼 쓰일 수 있다고 컴파일러에게 알려줘야한다.
    # MutableList<Any>가 MutalbeList<String>의 상위타입처럼 쓰일 수 있는 것은
    # MutableList<Any>에서는 값을 가져오기(get)만 하고(out)
    # MutableList<Any>가 값을 넣는 일(set, use)은 없기 때문이다.(in)

    # 여기서 list2: MutableList<out T>는 list2가 type T에 대해 공변적임을 나타낸다.
 */
fun <T> insertAll(list1: MutableList<T>, list2: MutableList<out T>){
    for(elm in list2) list1.add(elm)
}

//////////////////////////////// out exercise ////////////////////////////////////////

open class MyClassParent

class MyClass: MyClassParent()

interface Bag<out T>{ // T보다 상위타입인 타입이오면 상위타입처럼 쓰일 수 있다.
    fun get(): T // 반환형으로 사용
}

class BagImpl: Bag<MyClass>{ // MyClass의 상위인 Bag<MyClassParent>가 상위타입처럼 쓰일 수있다.
    override fun get(): MyClass {
        return MyClass()
    }
}

/////////////////////////////// in exercise ///////////////////////////////////////////

interface BaG<in T>{ // T보다 하위타입인 타입이 오면 상위타입처럼 쓰일 수 있다.
    fun use(t: T): Boolean // 인자로 사용
}

class BaGImpl: BaG<MyClassParent>{
    override fun use(t: MyClassParent): Boolean {
        return true
    }
}

fun main(){
    val bag: Bag<MyClassParent> = BagImpl() // Bag<MyClassParent>가 상위타입처럼 쓰임
    val baG: BaG<MyClass> = BaGImpl() // Bag<MyClass>가 상위타입처럼 쓰임
}