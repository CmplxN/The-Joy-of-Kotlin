package com.chapter_2.example

import java.time.Instant

/*
    classes, constructors and instances are public in default
    you can use private, internal(inter module), protected(inheritance)
    # 외부 클래스에 정의된 비공개 멤버를 내부 클래스에서 볼 수 없다?
 */

internal class Person2 private constructor(val name:String, val registered: Instant)

class Test{
    private val hi = 1
    inner class iTest{
        private val hello = 1
        fun TF(){
            println(hi) // 가능
        }
    }
    fun FF(){
        /*println(iTest().hello) 에러*/
    }
}