package com.chapter_2.example

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.Serializable
import java.time.Instant

/*
    # Default : pulbic final. have to use "open class" to inherit.
 */

class Person(val name: String, val registered: Instant): Serializable,
        Comparable<Person>{
    override fun compareTo(other: Person): Int {
        return (this.registered.epochSecond-other.registered.epochSecond).toInt()
    }
}

/*
    # data class ==> auto-made equals and hashCode, toString, copy, componentN
    call it by "Person1.create("123123")
 */

data class Person1(val name:String, val registered: Instant = Instant.now()){
    companion object{
        fun create(xml: String): Person1{
            TODO("Write an implementation creating "+
            "a Perons from an xml string")
        }
    }
}

/*
    Simple way to make a Singleton
    the name of object class itself becomes the singleton object.
 */

object MyWindowAdapter: WindowAdapter(){
    override fun windowClosed(e: WindowEvent?) {
        super.windowClosed(e)
    }
}

/*
    유틸리티 클래스의 인스턴스화 방지 : 클래스 밖의 패키지 수준에서 함수를 만들면 된다?
    그리고 val person = com.acme.util.create(someXmlString)식으로 해라
    쓰기 귀찮으면 import com.acme.util.*하고.
 */